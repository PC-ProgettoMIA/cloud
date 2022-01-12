package server.controllers;

import dataelaboration.ClimateStore;
import dataelaboration.model.csvmodel.DailyClimateData;
import dataelaboration.utility.DittoUtility;
import dataelaboration.utility.json.ObjectToJsonUtility;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

/**
 * Periodic controller.
 */
public class PeriodicDTController {

    private final ClimateStore climateStore;

    public PeriodicDTController(ClimateStore climateStore) {
        this.climateStore = climateStore;
    }


    /**
     * Average daily weather.
     *
     * @param ctx, context routing.
     */
    public void dailyWeatherSurveyDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId").replace(DittoUtility.THING_NAMESPACE, "");
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "application/json");
        DailyClimateData day = climateStore.lastDayAverageClimateData(thingId);
        response.end(ObjectToJsonUtility.dailyClimateDataToJson(
                thingId,
                day
        ).encodePrettily());
    }

    /**
     * Average weekly weather.
     *
     * @param ctx, context routing.
     */
    public void weeklyWeatherSurveyDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId").replace(DittoUtility.THING_NAMESPACE, "");
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "application/json");
        response.end(ObjectToJsonUtility.dailyClimateDataToJson(
                thingId,
                climateStore.lastWeekAverageClimateData(thingId)
        ).encodePrettily());
    }

    /**
     * Average monthly weather.
     *
     * @param ctx, context routing.
     */
    public void monthyWeatherSurveyDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId").replace(DittoUtility.THING_NAMESPACE, "");
        HttpServerResponse response = ctx.response();
        response.putHeader("content-type", "application/json");
        response.end(ObjectToJsonUtility.dailyClimateDataToJson(
                thingId,
                climateStore.lastMonthAverageClimateData(thingId)
        ).encodePrettily());
    }
}

