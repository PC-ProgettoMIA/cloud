package server.controllers;

import dataelaboration.ClimateStore;
import dataelaboration.utility.json.ObjectToJsonUtility;
import io.vertx.ext.web.RoutingContext;

/**
 * History controller.
 */
public class HistoryController {

    public ClimateStore climateStore;

    public HistoryController(ClimateStore climateStore) {
        this.climateStore = climateStore;
    }

    /**
     * Temperature history survey.
     *
     * @param ctx, context routing.
     */
    public void historyTemperatureSurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "temperature",
                climateStore.historyPropertySurveysDT(thingId, "temperature")).encodePrettily());
    }

    /**
     * Humidity history survey.
     *
     * @param ctx, context routing.
     */
    public void historyHumiditySurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "humidity",
                climateStore.historyPropertySurveysDT(thingId, "humidity")).encodePrettily());
    }

    /**
     * Pressure history survey.
     *
     * @param ctx, context routing.
     */
    public void historyPressureSurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "pressure",
                climateStore.historyPropertySurveysDT(thingId, "pressure")).encodePrettily());
    }

    /**
     * Co2 history survey.
     *
     * @param ctx, context routing.
     */
    public void historyCo2SurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "co2",
                climateStore.historyPropertySurveysDT(thingId, "co2")).encodePrettily());
    }

    /**
     * Tvoc history survey.
     *
     * @param ctx, context routing.
     */
    public void historyTvocSurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "tvoc",
                climateStore.historyPropertySurveysDT(thingId, "tvoc")).encodePrettily());
    }

    /**
     * Quality pm2_5 history survey.
     *
     * @param ctx, context routing.
     */
    public void historyPm2_5SurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "pm2_5",
                climateStore.historyPropertySurveysDT(thingId, "pm2_5")).encodePrettily());
    }

    /**
     * Quality pm1_0 history survey.
     *
     * @param ctx, context routing.
     */
    public void historyPm1_0SurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "pm1_0",
                climateStore.historyPropertySurveysDT(thingId, "pm1_0")).encodePrettily());
    }

    /**
     * Quality pm10 history survey.
     *
     * @param ctx, context routing.
     */
    public void historyPm10SurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "pm10",
                climateStore.historyPropertySurveysDT(thingId, "pm10")).encodePrettily());
    }

    /**
     * Wind history survey.
     *
     * @param ctx, context routing.
     */
    public void historyWindSurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "wind",
                climateStore.historyPropertySurveysDT(thingId, "wind")).encodePrettily());
    }

    /**
     * Rain history survey.
     *
     * @param ctx, context routing.
     */
    public void historyRainSurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.rainHistoryClimateDataToJson(thingId,
                climateStore.historyRainSurveysDT(thingId)).encodePrettily());
    }

    /**
     * Uv history survey.
     *
     * @param ctx, context routing.
     */
    public void historyUvSurveysDT(RoutingContext ctx) {
        String thingId = ctx.request().getParam("thingId");
        ctx.response().end(ObjectToJsonUtility.propertyHistoryClimateDataToJson(thingId, "uv",
                climateStore.historyPropertySurveysDT(thingId, "uv")).encodePrettily());
    }
}

