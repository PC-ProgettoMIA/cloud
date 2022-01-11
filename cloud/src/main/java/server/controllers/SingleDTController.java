package server.controllers;

import dataelaboration.ClimateStore;
import dataelaboration.model.csvmodel.InstantClimateData;
import dataelaboration.utility.Global;
import dataelaboration.utility.json.JsonToObjectUtility;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

/**
 * Single Digital twin controller.
 */
public class SingleDTController {

    private final WebClient client;
    private final ClimateStore climateStore;

    public SingleDTController(WebClient client, ClimateStore climateStore) {
        this.client = client;
        this.climateStore = climateStore;
    }


    /**
     * Digital twin getter.
     *
     * @param ctx, context routing.
     */
    public void getDigitalTwin(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        HttpRequest<Buffer> request = client.get(Global.DITTO_PORT, Global.DITTO_ADDRESS, "/api/2/things/" + thingId);
        MultiMap headers = request.headers();
        headers.set("content-type", "application/json");
        request.authentication(new UsernamePasswordCredentials("ditto", "ditto"));
        request.send()
                .onSuccess(res -> {
                    ctx.response().end(res.bodyAsJsonObject().encode());
                });
    }

    /**
     * Digital twin setter.
     *
     * @param ctx, context routing.
     */
    public void putDigitalTwin(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        JsonObject result = ctx.getBodyAsJson();
        if (result != null) {
            InstantClimateData data = new InstantClimateData(
                    JsonToObjectUtility.getJsonTimestamp(result),
                    JsonToObjectUtility.getJsonTemperature(result),
                    JsonToObjectUtility.getJsonHumidity(result),
                    JsonToObjectUtility.getJsonPressure(result),
                    JsonToObjectUtility.getJsonCo2(result),
                    JsonToObjectUtility.getJsonTvoc(result),
                    JsonToObjectUtility.getJsonPm2_5(result),
                    JsonToObjectUtility.getJsonPm1_0(result),
                    JsonToObjectUtility.getJsonPm10(result),
                    JsonToObjectUtility.getJsonWind(result),
                    JsonToObjectUtility.getJsonRain(result),
                    JsonToObjectUtility.getJsonUv(result)
            );
            this.climateStore.putInstantClimateSurvey(thingId.replace(Global.THING_NAMESPACE, ""), data);
            HttpRequest<Buffer> request = client.put(Global.DITTO_PORT, Global.DITTO_ADDRESS, "/api/2/things/" + thingId);
            MultiMap headers = request.headers();
            headers.set("content-type", "application/json");
            request.authentication(new UsernamePasswordCredentials("ditto", "ditto"));
            request.sendJsonObject(ctx.getBodyAsJson())
                    .onSuccess(res -> {
                        ctx.response().end("DT created/updated");
                    });

        }

    }
}

