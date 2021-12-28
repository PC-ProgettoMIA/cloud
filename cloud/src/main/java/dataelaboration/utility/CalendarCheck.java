package dataelaboration.utility;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Checker of date in date range.
 */
public class CalendarCheck {

    private static final long ONE_DAY_MILLISECONDS = 86400000L;
    private static final long ONE_WEEK_MILLISECONDS = 604800000L;
    private static final long ONE_MONTH_MILLISECONDS = 2592000000L;


    private static boolean checkDate(final Timestamp timestamp, final long period) {
        long today = new Timestamp(new Date().getTime()).getTime();
        today -= period;
        Timestamp time = new Timestamp(today);
        return time.before(timestamp);
    }

    /**
     * Check if timestamp is in current day.
     *
     * @param timestamp to check.
     * @return boolean check.
     */
    public static boolean isTimestampInCurrentDay(Timestamp timestamp) {
        return checkDate(timestamp, ONE_DAY_MILLISECONDS);
    }

    /**
     * Check if timestamp is in current week.
     *
     * @param timestamp to check.
     * @return boolean check.
     */
    public static boolean isDateInCurrentWeek(Timestamp timestamp) {
        return checkDate(timestamp, ONE_WEEK_MILLISECONDS);
    }

    /**
     * Check if timestamp is in current month.
     *
     * @param timestamp to check.
     * @return boolean check.
     */
    public static boolean isDateInCurrentMonth(Timestamp timestamp) {
        return checkDate(timestamp, ONE_MONTH_MILLISECONDS);
    }

}
