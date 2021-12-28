package server;

import dataelaboration.ClimateStore;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;
import server.controllers.HistoryController;
import server.controllers.PeriodicDTController;
import server.controllers.SingleDTController;

public class Receiver extends AbstractVerticle {

    private final int localPort;
    private final ClimateStore climateStore;
    private Routes routes;

    public Receiver(final int localPort, final ClimateStore climateStore) {
        this.localPort = localPort;
        this.climateStore = climateStore;
    }

    @Override
    public void start() throws Exception {
        initialize();
        getVertx().createHttpServer().requestHandler(this.routes.getRouter()).listen(localPort);
        System.out.println("Server online on port " + localPort);
    }

    private void initialize() {
        WebClient client = WebClient.create(getVertx());
        SingleDTController singleDTController = new SingleDTController(client, climateStore);
        PeriodicDTController periodicDTController = new PeriodicDTController(climateStore);
        HistoryController historyController = new HistoryController(climateStore);
        this.routes = new Routes(vertx, singleDTController, periodicDTController, historyController);
    }
}
