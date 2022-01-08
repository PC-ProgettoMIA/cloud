package server.controllers;

import dataelaboration.ClimateStore;
import dataelaboration.model.Coordinate;
import dataelaboration.model.InfoThing;
import dataelaboration.utility.json.JsonToObjectUtility;
import dataelaboration.utility.json.ObjectToJsonUtility;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data of Digital twin controller.
 */
public class SpatialController {

    public WebClient client;
    public ClimateStore climateStore;

    public SpatialController(WebClient client, ClimateStore climateStore) {
        this.client = client;
        this.climateStore = climateStore;
    }


    /**
     * Digital twin id and coordinate getter.
     *
     * @param ctx, context routing.
     */
    public void getSpatialStatusDigitalTwin(RoutingContext ctx) {
        JsonObject body = ctx.getBodyAsJson();

        if (body.containsKey("latitude1") && body.containsKey("longitude1") && body.containsKey("latitude2") && body.containsKey("longitude2")) {
            List<InfoThing> list = new ArrayList<>();

            HttpRequest<Buffer> request = client.get(8080, "localhost", "/api/2/search/things");
            MultiMap headers = request.headers();
            headers.set("content-type", "application/json");
            request.authentication(new UsernamePasswordCredentials("ditto", "ditto"));
            request.send()
                    .onSuccess(res -> {
                        JsonObject result = res.bodyAsJsonObject();
                        JsonArray items = result.getJsonArray("items");
                        for (int i = 0; i < items.size(); i++) {
                            String thingId = items.getJsonObject(i).getString("thingId");
                            String school = items.getJsonObject(i).getJsonObject("attributes").getString("school");
                            Coordinate coordinate = JsonToObjectUtility.getJsonCoordinates(items.getJsonObject(i));
                            list.add(new InfoThing(thingId, school, coordinate));
                        }
                        ctx.response().end(
                                ObjectToJsonUtility.dailyClimateDataToJson(
                                        "Geographical Area", climateStore.lastDayAverageAreaClimate(
                                                list.stream()
                                                        .filter(x -> x.getCoordinate().getLatitude() > body.getDouble("latitude1")
                                                                && x.getCoordinate().getLongitude() > body.getDouble("longitude1"))
                                                        .filter(x -> x.getCoordinate().getLatitude() < body.getDouble("latitude2")
                                                                && x.getCoordinate().getLongitude() < body.getDouble("longitude2"))
                                                        .map(x -> x.getThingId().replace("my.houses:", ""))
                                                        .map(e -> climateStore.lastDayAverageClimateData(e))
                                                        .collect(Collectors.toList())
                                        )
                                ).encodePrettily());
                    });

        }
    }

}

