package dataelaboration;

import dataelaboration.model.DailyClimateData;
import dataelaboration.model.InstantClimateData;
import dataelaboration.utility.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Climate store.
 */
public class ClimateStore {


    /*
        public List<DailyClimateData> lastWeekWeatherSurveysDT(final String thingId) {
            try {
                List<DailyClimateData> climateData = CsvReadWrite.getHistoryClimateSurveys();
                return climateData.stream()
                        .filter(x -> Objects.equals(thingId, x.getThingID()))
                        .filter(x -> CalendarCheck.isDateInCurrentWeek(DateConversion.stringToTimestamp(x.getTimestamp())))
                        .collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    */

    /*public List<InstantClimateData> dailyWeatherSurveyDT(final String thingId) {
    try {
        return CsvReadWrite.getDailyClimateSurvey(thingId).stream()
                .filter(x -> CalendarCheck.isTimestampInCurrentDay(DateConversion.stringToTimestamp(x.getTimestamp())))
                .collect(Collectors.toList());
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}*/


    /**
     * Average of last day climate.
     *
     * @param thingId, thing id of house.
     * @return average of last day climate.
     */
    public DailyClimateData lastDayAverageClimateData(final String thingId) {

        List<InstantClimateData> list = new ArrayList<>();
        try {
            list = CsvReadWrite.getDailyClimateSurvey(thingId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<InstantClimateData> averageList = list.stream()
                .filter(x -> CalendarCheck.isTimestampInCurrentDay(DateConversion.stringToTimestamp(x.getTimestamp())))
                .collect(Collectors.toList());
        DailyClimateData avgClimateData = new DailyClimateData();
        if (!averageList.isEmpty()) {
            avgClimateData.setAvgtemp(((float) averageList.stream().mapToDouble(InstantClimateData::getTemperature).average().getAsDouble()));
            avgClimateData.setMintemp(((float) averageList.stream().mapToDouble(InstantClimateData::getTemperature).min().getAsDouble()));
            avgClimateData.setMaxtemp(((float) averageList.stream().mapToDouble(InstantClimateData::getTemperature).max().getAsDouble()));
            avgClimateData.setAvghum(((float) averageList.stream().mapToDouble(InstantClimateData::getHumidity).average().getAsDouble()));
            avgClimateData.setAvgpress(((float) averageList.stream().mapToDouble(InstantClimateData::getPressure).average().getAsDouble()));
            avgClimateData.setMinpress(((float) averageList.stream().mapToDouble(InstantClimateData::getPressure).min().getAsDouble()));
            avgClimateData.setMaxpress(((float) averageList.stream().mapToDouble(InstantClimateData::getPressure).max().getAsDouble()));
            avgClimateData.setAvgco2(((float) averageList.stream().mapToDouble(InstantClimateData::getCo2).average().getAsDouble()));
            avgClimateData.setAvgtvoc(((float) averageList.stream().mapToDouble(InstantClimateData::getTvoc).average().getAsDouble()));
            avgClimateData.setAvgpm2_5(((float) averageList.stream().mapToDouble(InstantClimateData::getPm2_5).average().getAsDouble()));
            avgClimateData.setAvgpm1_0(((float) averageList.stream().mapToDouble(InstantClimateData::getPm1_0).average().getAsDouble()));
            avgClimateData.setAvgpm10(((float) averageList.stream().mapToDouble(InstantClimateData::getPm10).average().getAsDouble()));
            avgClimateData.setAvgwind(((float) averageList.stream().mapToDouble(InstantClimateData::getWind).average().getAsDouble()));
            avgClimateData.setMaxwind(((float) averageList.stream().mapToDouble(InstantClimateData::getWind).max().getAsDouble()));
            avgClimateData.setAvguv(((float) averageList.stream().mapToDouble(InstantClimateData::getUv).average().getAsDouble()));
        /*for (InstantClimateData climateDatum : averageList) {
            avgClimateData.setAvgtemp(climateDatum.getTemperature() + avgClimateData.getAvgtemp());
            if (avgClimateData.getMintemp() > climateDatum.getTemperature() || avgClimateData.getMintemp() == Float.MIN_VALUE)
                avgClimateData.setMintemp(climateDatum.getTemperature());
            if (avgClimateData.getMaxtemp() < climateDatum.getTemperature() || avgClimateData.getMaxtemp() == Float.MIN_VALUE)
                avgClimateData.setMaxtemp(climateDatum.getTemperature());
            avgClimateData.setAvghum(climateDatum.getHumidity() + avgClimateData.getAvghum());
            avgClimateData.setAvgpress(climateDatum.getPressure() + avgClimateData.getAvgpress());
            if (avgClimateData.getMinpress() > climateDatum.getPressure() || avgClimateData.getMinpress() == Float.MIN_VALUE)
                avgClimateData.setMinpress(climateDatum.getPressure());
            if (avgClimateData.getMaxpress() < climateDatum.getPressure() || avgClimateData.getMaxpress() == Float.MIN_VALUE)
                avgClimateData.setMaxpress(climateDatum.getPressure());
            avgClimateData.setAvgco2(climateDatum.getCo2() + avgClimateData.getAvgco2());
            avgClimateData.setAvgtvoc(climateDatum.getTvoc() + avgClimateData.getAvgtvoc());
            avgClimateData.setAvgpm2_5(climateDatum.getPm2_5() + avgClimateData.getAvgpm2_5());
            avgClimateData.setAvgpm1_0(climateDatum.getPm1_0() + avgClimateData.getAvgpm1_0());
            avgClimateData.setAvgpm10(climateDatum.getPm10() + avgClimateData.getAvgpm10());
            avgClimateData.setAvgwind(climateDatum.getWind() + avgClimateData.getAvgwind());
            if (avgClimateData.getMaxwind() < climateDatum.getWind() || avgClimateData.getMaxwind() == Float.MIN_VALUE)
                avgClimateData.setMaxwind(climateDatum.getWind());
            avgClimateData.setAvguv(climateDatum.getUv() + avgClimateData.getAvguv());
        }
        avgClimateData.setAvgtemp(avgClimateData.getAvgtemp() / averageList.size());
        avgClimateData.setAvghum(avgClimateData.getAvghum() / averageList.size());
        avgClimateData.setAvgpress(avgClimateData.getAvgpress() / averageList.size());
        avgClimateData.setAvgco2(avgClimateData.getAvgco2() / averageList.size());
        avgClimateData.setAvgtvoc(avgClimateData.getAvgtvoc() / averageList.size());
        avgClimateData.setAvgpm2_5(avgClimateData.getAvgpm2_5() / averageList.size());
        avgClimateData.setAvgpm1_0(avgClimateData.getAvgpm1_0() / averageList.size());
        avgClimateData.setAvgpm10(avgClimateData.getAvgpm10() / averageList.size());
        avgClimateData.setAvgwind(avgClimateData.getAvgwind() / averageList.size());
        avgClimateData.setAvguv(avgClimateData.getAvguv() / averageList.size());*/
        }
        return avgClimateData;
    }

    /**
     * Average of last week climate.
     *
     * @param thingId, thing id of house.
     * @return average of last week climate.
     */
    public DailyClimateData lastWeekAverageClimateData(final String thingId) {
        List<InstantClimateData> list = new ArrayList<>();
        try {
            list = CsvReadWrite.getDailyClimateSurvey(thingId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<InstantClimateData> averageList = list.stream()
                .filter(x -> CalendarCheck.isTimestampInCurrentDay(DateConversion.stringToTimestamp(x.getTimestamp())))
                .collect(Collectors.toList());
        DailyClimateData avgClimateData = new DailyClimateData();
        if (!averageList.isEmpty()) {
            avgClimateData.setAvgtemp(((float) averageList.stream().mapToDouble(InstantClimateData::getTemperature).average().getAsDouble()));
            avgClimateData.setMintemp(((float) averageList.stream().mapToDouble(InstantClimateData::getTemperature).min().getAsDouble()));
            avgClimateData.setMaxtemp(((float) averageList.stream().mapToDouble(InstantClimateData::getTemperature).max().getAsDouble()));
            avgClimateData.setAvghum(((float) averageList.stream().mapToDouble(InstantClimateData::getHumidity).average().getAsDouble()));
            avgClimateData.setAvgpress(((float) averageList.stream().mapToDouble(InstantClimateData::getPressure).average().getAsDouble()));
            avgClimateData.setMinpress(((float) averageList.stream().mapToDouble(InstantClimateData::getPressure).min().getAsDouble()));
            avgClimateData.setMaxpress(((float) averageList.stream().mapToDouble(InstantClimateData::getPressure).max().getAsDouble()));
            avgClimateData.setAvgco2(((float) averageList.stream().mapToDouble(InstantClimateData::getCo2).average().getAsDouble()));
            avgClimateData.setAvgtvoc(((float) averageList.stream().mapToDouble(InstantClimateData::getTvoc).average().getAsDouble()));
            avgClimateData.setAvgpm2_5(((float) averageList.stream().mapToDouble(InstantClimateData::getPm2_5).average().getAsDouble()));
            avgClimateData.setAvgpm1_0(((float) averageList.stream().mapToDouble(InstantClimateData::getPm1_0).average().getAsDouble()));
            avgClimateData.setAvgpm10(((float) averageList.stream().mapToDouble(InstantClimateData::getPm10).average().getAsDouble()));
            avgClimateData.setAvgwind(((float) averageList.stream().mapToDouble(InstantClimateData::getWind).average().getAsDouble()));
            avgClimateData.setMaxwind(((float) averageList.stream().mapToDouble(InstantClimateData::getWind).max().getAsDouble()));
            avgClimateData.setAvguv(((float) averageList.stream().mapToDouble(InstantClimateData::getUv).average().getAsDouble()));
        }
        return avgClimateData;
    }

    /**
     * Average of last month climate.
     *
     * @param thingId, thing id of house.
     * @return average of last month climate.
     */
    public DailyClimateData lastMonthAverageClimateData(final String thingId) {
        try {
            List<DailyClimateData> averageList = CsvReadWrite.getHistoryClimateSurveys().stream()
                    .filter(x -> Objects.equals(x.getThingID(), thingId))
                    .filter(x -> CalendarCheck.isDateInCurrentMonth(DateConversion.stringToTimestamp(x.getTimestamp())))
                    .collect(Collectors.toList());
            DailyClimateData avgClimateData = new DailyClimateData();
            if (!averageList.isEmpty()) {
                avgClimateData.setAvgtemp(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgtemp).average().getAsDouble()));
                avgClimateData.setMintemp(((float) averageList.stream().mapToDouble(DailyClimateData::getMintemp).min().getAsDouble()));
                avgClimateData.setMaxtemp(((float) averageList.stream().mapToDouble(DailyClimateData::getMaxtemp).max().getAsDouble()));
                avgClimateData.setAvghum(((float) averageList.stream().mapToDouble(DailyClimateData::getAvghum).average().getAsDouble()));
                avgClimateData.setAvgpress(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgpress).average().getAsDouble()));
                avgClimateData.setMinpress(((float) averageList.stream().mapToDouble(DailyClimateData::getMinpress).min().getAsDouble()));
                avgClimateData.setMaxpress(((float) averageList.stream().mapToDouble(DailyClimateData::getMaxpress).max().getAsDouble()));
                avgClimateData.setAvgco2(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgco2).average().getAsDouble()));
                avgClimateData.setAvgtvoc(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgtvoc).average().getAsDouble()));
                avgClimateData.setAvgpm2_5(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgpm2_5).average().getAsDouble()));
                avgClimateData.setAvgpm1_0(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgpm1_0).average().getAsDouble()));
                avgClimateData.setAvgpm10(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgpm10).average().getAsDouble()));
                avgClimateData.setAvgwind(((float) averageList.stream().mapToDouble(DailyClimateData::getAvgwind).average().getAsDouble()));
                avgClimateData.setMaxwind(((float) averageList.stream().mapToDouble(DailyClimateData::getMaxwind).max().getAsDouble()));
                avgClimateData.setAvguv(((float) averageList.stream().mapToDouble(DailyClimateData::getAvguv).average().getAsDouble()));
            }
            return avgClimateData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Climate history of rain in specific house.
     *
     * @param thingId of specific house.
     * @return list of climate data.
     */
    public List<Tuple<String, Boolean>> historyRainSurveysDT(final String thingId) {
        try {
            return CsvReadWrite.getDailyClimateSurvey(thingId).stream()
                    .map(x -> new Tuple<>(x.getTimestamp(), x.isRain()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Climate history of property in specific house.
     *
     * @param thingId  of specific house.
     * @param property to create a specific list.
     * @return list of climate data.
     */
    public List<Tuple<String, Float>> historyPropertySurveysDT(final String thingId, final String property) {
        try {
            List<InstantClimateData> list = CsvReadWrite.getDailyClimateSurvey(thingId);
            List<Tuple<String, Float>> finalList = null;
            switch (property) {
                case "temperature":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getTemperature()))
                            .collect(Collectors.toList());
                    break;
                case "humidity":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getHumidity()))
                            .collect(Collectors.toList());
                    break;
                case "pressure":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getPressure()))
                            .collect(Collectors.toList());
                    break;
                case "co2":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getCo2()))
                            .collect(Collectors.toList());
                    break;
                case "tvoc":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getTvoc()))
                            .collect(Collectors.toList());
                    break;
                case "pm2_5":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getPm2_5()))
                            .collect(Collectors.toList());
                    break;
                case "pm1_0":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getPm1_0()))
                            .collect(Collectors.toList());
                    break;
                case "pm10":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getPm10()))
                            .collect(Collectors.toList());
                    break;
                case "wind":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getWind()))
                            .collect(Collectors.toList());
                    break;
                //case "rain" : finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.isRain()))
                //        .collect(Collectors.toList());
                // break;
                case "uv":
                    finalList = list.stream().map(x -> new Tuple<>(x.getTimestamp(), x.getUv()))
                            .collect(Collectors.toList());
                    break;
            }
            return finalList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Insert in csv file new record.
     *
     * @param thingId of the specific house.
     * @param data    to insert.
     */
    public void putInstantClimateSurvey(final String thingId, final InstantClimateData data) {
        List<InstantClimateData> list = new ArrayList<>();
        if (FileManager.existHouseMiaCsv(thingId)) {
            try {
                list = CsvReadWrite.getDailyClimateSurvey(thingId);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            list.add(data);
            CsvReadWrite.setDailyClimateSurvey(list, thingId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove all data older than one week.
     *
     * @param thingId of the specific house.
     */
    public void removeOldWeeklyClimateSurvey(final String thingId) {
        List<InstantClimateData> list;
        try {
            list = CsvReadWrite.getDailyClimateSurvey(thingId);
            CsvReadWrite.setDailyClimateSurvey(
                    list.stream()
                            .filter(x -> CalendarCheck.isDateInCurrentWeek(DateConversion.stringToTimestamp(x.getTimestamp())))
                            .collect(Collectors.toList()),
                    thingId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save last day data in average history file.
     *
     * @param thingId of the specific house.
     */
    public void updateLastDayAverageInClimateHistoryData(final String thingId) {
        List<DailyClimateData> updateList;
        try {
            updateList = CsvReadWrite.getHistoryClimateSurveys();
            DailyClimateData lastDay = lastDayAverageClimateData(thingId);
            lastDay.setThingID(thingId);
            long timestamp = (new Date().getTime() - 14400000L);
            lastDay.setTimestamp(String.valueOf(timestamp));
            updateList.add(lastDay);
            CsvReadWrite.setHistoryClimateSurvey(updateList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove all history data older than one month.
     */
    public void removeOldMonthlyClimateHistoryData() {
        List<DailyClimateData> list;
        try {
            list = CsvReadWrite.getHistoryClimateSurveys();
            CsvReadWrite.setHistoryClimateSurvey(list.stream()
                    .filter(x -> CalendarCheck.isDateInCurrentMonth(DateConversion.stringToTimestamp(x.getTimestamp())))
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
