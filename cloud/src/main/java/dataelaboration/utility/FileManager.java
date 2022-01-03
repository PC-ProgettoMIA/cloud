package dataelaboration.utility;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class FileManager {

    public static final String PATH_TO_PROJECT = System.getProperty("user.dir") + System.getProperty("file.separator");
    private static final String HOUSE_PATH = PATH_TO_PROJECT + "src/main/resources/csv/lastweek";
    public static final Path HISTORY_PATH = Paths.get(PATH_TO_PROJECT + "src/main/resources/csv/history.csv");


    /**
     * Path of specific house csv file.
     *
     * @param thingId, thing's id.
     * @return path of specific house.
     */
    public static Path housePath(final String thingId) {
        return Paths.get(HOUSE_PATH + "/" + thingId + ".csv");
    }

    /**
     * Get all house mia csv files.
     *
     * @return array of files.
     */
    public static String[] getHouseMiaFiles() {
        File directoryPath = new File(HOUSE_PATH);
        return directoryPath.list();
    }

    /**
     * Check if housemia.csv file exist.
     *
     * @param thingId thing id of specific file.
     * @return boolean value of check.
     */
    public static boolean existHouseMiaCsv(final String thingId) {
        for (String element : getHouseMiaFiles()) {
            if (Objects.equals(element, thingId + ".csv"))
                return true;
        }
        return false;
    }
}
