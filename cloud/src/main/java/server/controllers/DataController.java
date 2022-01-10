package server.controllers;

import dataelaboration.model.Coordinate;
import dataelaboration.model.InfoThing;
import dataelaboration.utility.Global;
import dataelaboration.utility.json.JsonToObjectUtility;
import dataelaboration.utility.json.ObjectToJsonUtility;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Data of Digital twin controller.
 */
public class DataController {

    private final WebClient client;

    public DataController(WebClient client) {
        this.client = client;
    }


    /**
     * Digital twin id and coordinate getter.
     *
     * @param ctx, context routing.
     */
    public void getIdCoordinateOfDigitalTwin(RoutingContext ctx) {
        List<InfoThing> list = new ArrayList<>();

        HttpRequest<Buffer> request = client.get(Global.DITTO_PORT, Global.DITTO_ADDRESS, "/api/2/search/things");
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
                    HttpServerResponse response = ctx.response();
                    response.putHeader("content-type", "application/json");
                    response.end(ObjectToJsonUtility.IdCoordinateDataToJson(list).encodePrettily());
                });
    }
}

