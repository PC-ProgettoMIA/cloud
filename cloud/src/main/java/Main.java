import dataelaboration.ClimateStore;
import dataelaboration.utility.FileManager;
import dataelaboration.utility.Log;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import server.Receiver;

public class Main {


    private static final int CLOUD_PORT = 3128;
    private static final int DAY_IN_MILLIS = 86400000;

    public static void main(String[] args) {
        try {
            ClimateStore climateStore = new ClimateStore();
            AbstractVerticle service = new Receiver(CLOUD_PORT, climateStore);
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(service);

            long timerID = vertx.setPeriodic(DAY_IN_MILLIS, new Handler<Long>() {
                @Override
                public void handle(Long aLong) {
                    //Manage weekly files
                    for (String content : FileManager.getHouseMiaFiles()) {
                        String file = content.replace(".csv", "");
                        climateStore.removeOldWeeklyClimateSurvey(file);
                        //Manage history file
                        climateStore.updateLastDayAverageInClimateHistoryData(file);
                    }
                    //Remove all data after one month
                    climateStore.removeOldMonthlyClimateHistoryData();
                }
            });
        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }


}

