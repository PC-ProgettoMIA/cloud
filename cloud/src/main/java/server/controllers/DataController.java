package server.controllers;

import dataelaboration.ClimateStore;
import dataelaboration.model.Coordinate;
import dataelaboration.model.Tuple;
import dataelaboration.utility.FileManager;
import dataelaboration.utility.json.JsonToObjectUtility;
import dataelaboration.utility.json.ObjectToJsonUtility;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;

import java.util.LinkedList;
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
        List<Tuple<String, Coordinate>> list = new LinkedList<>();
        for (String element : FileManager.getHouseMiaFiles()) {
            String thingId = element.replace(".csv", "");
            HttpRequest<Buffer> request = client.get(8080, "localhost", "/api/2/things/my.houses:" + thingId);
            MultiMap headers = request.headers();
            headers.set("content-type", "application/json");
            request.authentication(new UsernamePasswordCredentials("ditto", "ditto"));
            request.send()
                    .onSuccess(res -> {
                        System.out.println("thingId " + thingId);
                        Coordinate coordinate = JsonToObjectUtility.getJsonCoordinates(res.bodyAsJsonObject());
                        System.out.println("coordinate " + coordinate);
                        list.add(new Tuple<>(thingId, coordinate));
                    });

            list.add(new Tuple<>(thingId, new Coordinate(12.2, 13.3)));
        }
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "application/json");
        response.end(ObjectToJsonUtility.IdCoordinateDataToJson(list).encodePrettily());
    }
}

