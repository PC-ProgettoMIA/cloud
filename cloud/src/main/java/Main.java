import dataelaboration.ClimateStore;
import dataelaboration.utility.FileManager;
import dataelaboration.utility.Log;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import server.Receiver;

public class Main {


    public static void main(String[] args) {
        try {
            ClimateStore climateStore = new ClimateStore();
            AbstractVerticle service = new Receiver(3128, climateStore);
            Vertx vertx = Vertx.vertx();
            vertx.deployVerticle(service);

            //long timerID = vertx.setPeriodic(86400000, new Handler<Long>() {
            long timerID = vertx.setPeriodic(3600000, new Handler<Long>() {

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

