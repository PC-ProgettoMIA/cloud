package dataelaboration.utility;


import java.sql.Timestamp;

/**
 * Date converter.
 */
public final class DateConversion {

    /**
     * String to Timestamp conversion.
     *
     * @param timestamp string to convert.
     * @return the respective timestamp.
     */
    public static Timestamp stringToTimestamp(String timestamp) {
        long tsTime2 = Long.parseLong(timestamp);
        return new Timestamp(tsTime2);
    }

    /**
     * Timestamp to String conversion.
     *
     * @param timestamp to convert.
     * @return the respective string.
     */
    public static String timestampToString(Timestamp timestamp) {
        return timestamp.toString();
    }

}
