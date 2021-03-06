package server;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import server.controllers.*;

public class Routes {
    private static final int ERROR_CODE = 404;
    private final Router router;

    Routes(Vertx vertx,
           SingleDTController singleDTController, PeriodicDTController periodicDTController, HistoryController historyController,
           DataController dataController, SpatialController spatialController) {
        this.router = Router.router(vertx);

        router.route()
                .handler(BodyHandler.create())
                .handler(CorsHandler.create()
                        .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                        .allowCredentials(true)
                        .allowedHeader("Access-Control-Allow-Headers")
                        .allowedHeader("Authorization")
                        .allowedHeader("Access-Control-Allow-Method")
                        .allowedHeader("Access-Control-Allow-Origin")
                        .allowedHeader("Access-Control-Allow-Credentials")
                        .allowedHeader("Content-Type"));


        router.put("/api/ditto/:thingId").handler(singleDTController::putDigitalTwin);
        router.get("/api/ditto/:thingId").handler(singleDTController::getDigitalTwin);

        router.get("/api/daily/:thingId").handler(periodicDTController::dailyWeatherSurveyDT);
        router.get("/api/weekly/:thingId").handler(periodicDTController::weeklyWeatherSurveyDT);
        router.get("/api/monthly/:thingId").handler(periodicDTController::monthyWeatherSurveyDT);

        router.get("/api/history/temperature/:thingId").handler(historyController::historyTemperatureSurveysDT);
        router.get("/api/history/humidity/:thingId").handler(historyController::historyHumiditySurveysDT);
        router.get("/api/history/pressure/:thingId").handler(historyController::historyPressureSurveysDT);
        router.get("/api/history/co2/:thingId").handler(historyController::historyCo2SurveysDT);
        router.get("/api/history/tvoc/:thingId").handler(historyController::historyTvocSurveysDT);
        router.get("/api/history/pm2_5/:thingId").handler(historyController::historyPm2_5SurveysDT);
        router.get("/api/history/pm1_0/:thingId").handler(historyController::historyPm1_0SurveysDT);
        router.get("/api/history/pm10/:thingId").handler(historyController::historyPm10SurveysDT);
        router.get("/api/history/wind/:thingId").handler(historyController::historyWindSurveysDT);
        router.get("/api/history/rain/:thingId").handler(historyController::historyRainSurveysDT);
        router.get("/api/history/uv/:thingId").handler(historyController::historyUvSurveysDT);

        router.get("/api/spatial").handler(spatialController::getSpatialStatusDigitalTwin);

        router.get("/api/all/things").handler(dataController::getIdCoordinateOfDigitalTwin);

        router.errorHandler(ERROR_CODE, routingContext -> {
            routingContext.response().setStatusCode(ERROR_CODE).end("Route not found");
        });

    }

    Router getRouter() {
        return this.router;
    }


}
