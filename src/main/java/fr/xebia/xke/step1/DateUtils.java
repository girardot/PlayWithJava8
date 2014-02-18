package fr.xebia.xke.step1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter DATE_PARSER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter DATE_TIME_PARSER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.");

    /**
     * Parse String date without times
     *
     * @param date format  yyyy-MM-dd
     * @return
     */
    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_PARSER);
    }

    /**
     * parse String date with time
     *
     * @param date format  yyyy-MM-dd'T'HH:mm:ss.
     * @return
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE_PARSER);
    }

    public static int age(LocalDate birthday, LocalDate now) {
        return Period.between(birthday, now).getYears();
    }

    public static LocalDateTime addDuration(LocalDateTime date, int durationInMinute) {
        return date.plusMinutes(durationInMinute);
    }

    public static LocalDateTime dayDateWithTime(LocalDate day, int hour, int minute, int second) {
        return day.atTime(hour, minute, second);
    }

    /**
     * @param firstDateWithTime  format  yyyy-MM-dd'T'HH:mm:ss.
     * @param secondDateWithTime format  yyyy-MM-dd'T'HH:mm:ss.
     * @return
     */
    public static boolean dayAreEquals(String firstDateWithTime, String secondDateWithTime) {
        LocalDateTime firstLocalDateTime = parseDateTime(firstDateWithTime);

        LocalDateTime secondLocalDateTime = parseDateTime(secondDateWithTime);

        return firstLocalDateTime.toLocalDate().equals(secondLocalDateTime.toLocalDate());
    }
}
