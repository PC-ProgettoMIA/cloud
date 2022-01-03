package dataelaboration.utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Log {

    private static final String LOG_PATH = FileManager.PATH_TO_PROJECT + "src/main/resources/exception.log";
    private static Logger log = Logger.getLogger("Log");
    private static FileHandler fh;

    public static void info(final String msg) {
        try {
            fh = new FileHandler(LOG_PATH, true);
            log.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        log.info(msg);
    }

    public static void main(String[] args) {
        Log.info("Ciao");

    }
}
