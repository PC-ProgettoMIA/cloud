package dataelaboration.utility;

import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import dataelaboration.model.csvmodel.DailyClimateData;
import dataelaboration.model.csvmodel.InstantClimateData;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility for read and write csv file.
 */
public class CsvReadWrite {


    /**
     * Get history climate survey from respective csv file.
     *
     * @return list of climate data.
     * @throws IOException exception in read or write.
     */
    public static List<DailyClimateData> getHistoryClimateSurveys() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(FileManager.HISTORY_PATH,
                StandardCharsets.UTF_8)) {
            ColumnPositionMappingStrategy<DailyClimateData> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(DailyClimateData.class);
            String[] fields = {"thingId", "timestamp", "avgtemp", "mintemp", "maxtemp",
                    "avghum", "avgpress", "minpress", "maxpress", "avgco2", "avgtvoc",
                    "avgpm2_5", "avgpm1_0", "avgpm10", "avgwind", "maxwind", "avguv"
            };
            strategy.setColumnMapping(fields);
            CsvToBean<DailyClimateData> csvToBean = new CsvToBeanBuilder<DailyClimateData>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }

    /**
     * Get daily climate survey from respective csv file.
     *
     * @return list of climate data.
     * @throws IOException exception in read or write.
     */
    public static List<InstantClimateData> getDailyClimateSurvey(final String thingId) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(FileManager.housePath(thingId),
                StandardCharsets.UTF_8)) {
            ColumnPositionMappingStrategy<InstantClimateData> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(InstantClimateData.class);
            String[] fields = {"timestamp", "temperature", "humidity",
                    "pressure", "co2", "tvoc", "pm2_5", "pm1_0", "pm10", "wind", "rain", "uv"
            };

            strategy.setColumnMapping(fields);
            CsvToBean<InstantClimateData> csvToBean = new CsvToBeanBuilder<InstantClimateData>(br)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();
        }
    }

    /**
     * Set daily climate survey from respective csv file.
     *
     * @param list,    list to write.
     * @param thingId, thing's id.
     * @throws IOException exception in read or write.
     */
    public static void setDailyClimateSurvey(final List<InstantClimateData> list, final String thingId) throws IOException {
        try (var writer = Files.newBufferedWriter(FileManager.housePath(thingId), StandardCharsets.UTF_8)) {

            StatefulBeanToCsv<InstantClimateData> beanToCsv = new StatefulBeanToCsvBuilder<InstantClimateData>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(list);

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            Logger.getLogger(CsvReadWrite.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
        }
    }


    /**
     * Set history climate survey from respective csv file.
     *
     * @param list, list to write.
     * @throws IOException exception in read or write.
     */
    public static void setHistoryClimateSurvey(final List<DailyClimateData> list) throws IOException {
        try (var writer = Files.newBufferedWriter(FileManager.HISTORY_PATH, StandardCharsets.UTF_8)) {

            StatefulBeanToCsv<DailyClimateData> beanToCsv = new StatefulBeanToCsvBuilder<DailyClimateData>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(list);

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException |
                IOException ex) {
            Logger.getLogger(CsvReadWrite.class.getName()).log(
                    Level.SEVERE, ex.getMessage(), ex);
        }
    }


//    public static void main(String[] args) throws Exception {
//
//        System.out.println(new CsvReader().dailyWeatherSurveyDT("01").toString());
//
//        System.out.println(new CsvReader().lastWeekWeatherSurveysDT("100").toString());
//
//        System.out.println(new CsvReader().lastWeekWeatherSurveysAggregate().toString());
//    }

}
