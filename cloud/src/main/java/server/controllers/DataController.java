package server.controllers;

import dataelaboration.ClimateStore;
import dataelaboration.model.Coordinate;
import dataelaboration.model.Tuple;
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

    public WebClient client;
    public ClimateStore climateStore;

    public DataController(WebClient client, ClimateStore climateStore) {
        this.client = client;
        this.climateStore = climateStore;
    }


    /**
     * Digital twin id and coordinate getter.
     *
     * @param ctx, context routing.
     */
    public void getIdCoordinateOfDigitalTwin(RoutingContext ctx) {
        List<Tuple<String, Tuple<String, Coordinate>>> list = new ArrayList<>();

        HttpRequest<Buffer> request = client.get(8080, "localhost", "/api/2/search/things");
        MultiMap headers = request.headers();
        headers.set("content-type", "application/json");
        request.authentication(new UsernamePasswordCredentials("ditto", "ditto"));
        request.send()
                .onSuccess(res -> {
                    JsonObject result = res.bodyAsJsonObject();
                    JsonArray items = result.getJsonArray("items");
                    for (int i = 0; i < items.size(); i++) {
                        String thingId = items.getJsonObject(i).getString("thingId").split(":")[1];
                        String school = items.getJsonObject(i).getJsonObject("attributes").getString("school");
                        Coordinate coordinate = JsonToObjectUtility.getJsonCoordinates(items.getJsonObject(i));
                        list.add(new Tuple<>(thingId, new Tuple<>(school, new Coordinate(coordinate.getLatitude(), coordinate.getLongitude()))));
                    }
                    HttpServerResponse response = ctx.response();
                    response.putHeader("content-type", "application/json");
                    response.end(ObjectToJsonUtility.IdCoordinateDataToJson(list).encodePrettily());
                });
    }
}

