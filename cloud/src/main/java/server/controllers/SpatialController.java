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
        String latitude1 = ctx.request().getParam("latitude1");
        String longitude1 = ctx.request().getParam("longitude1");
        String latitude2 = ctx.request().getParam("latitude2");
        String longitude2 = ctx.request().getParam("longitude2");

        if (!latitude1.isEmpty() && !longitude1.isEmpty() && !latitude2.isEmpty() && !longitude2.isEmpty()) {
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
                        JsonArray jsonArray = new JsonArray();
                        list.stream()
                                .filter(x -> x.getCoordinate().getLatitude() > Double.parseDouble(latitude1)
                                        && x.getCoordinate().getLongitude() > Double.parseDouble(longitude1))
                                .filter(x -> x.getCoordinate().getLatitude() < Double.parseDouble(latitude2)
                                        && x.getCoordinate().getLongitude() < Double.parseDouble(longitude2))
                                .map(x -> x.getSchoolName())
                                .map(e -> new JsonObject().put("name", e)).forEach(jsonArray::add);
                        ctx.response().end(
                                ObjectToJsonUtility.geographicalAverageDataToJson(
                                                climateStore.lastDayAverageAreaClimate(
                                                        list.stream()
                                                                .filter(x -> x.getCoordinate().getLatitude() > Double.parseDouble(latitude1)
                                                                        && x.getCoordinate().getLongitude() > Double.parseDouble(longitude1))
                                                                .filter(x -> x.getCoordinate().getLatitude() < Double.parseDouble(latitude2)
                                                                        && x.getCoordinate().getLongitude() < Double.parseDouble(longitude2))
                                                                .map(x -> x.getThingId().replace("my.houses:", ""))
                                                                .map(e -> climateStore.lastDayAverageClimateData(e))
                                                                .collect(Collectors.toList())
                                                )
                                        ).put("schools", jsonArray)
                                        .encodePrettily());
                    });

        }
    }

}

