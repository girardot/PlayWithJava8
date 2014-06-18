package fr.xebia.xke.java8.step1;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    /**
     * Parse String date without times
     *
     * @param date format  dd/MM/yyyy
     * @return LocalDate
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }

    /**
     * parse String date with time
     *
     * @param date format  dd/MM/yyyy HH:mm:ss
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static int age(LocalDate birthday, LocalDate now) {
        Period period = Period.between(birthday, now);
        return period.getYears();
    }

    public static LocalDateTime dayDateWithTime(LocalDate dayDate, int hour, int minute, int second) {
        return LocalDateTime.of(dayDate, LocalTime.of(hour, minute, second));
    }

    public static LocalDateTime addDuration(LocalDateTime date, int minute) {
        Duration duration = Duration.ofMinutes(minute);
        return date.plus(duration);
    }

    /**
     * @param firstDateWithTime  format  yyyy-MM-dd'T'HH:mm:ss
     * @param secondDateWithTime format  yyyy-MM-dd'T'HH:mm:ss
     * @return boolean
     */
    public static boolean dayAreEquals(LocalDateTime firstDateWithTime, LocalDateTime secondDateWithTime) {
        return firstDateWithTime.getDayOfMonth() == secondDateWithTime.getDayOfMonth();
    }

    public static String convertToTimeZone(String dateWithTime, ZoneId timeZoneFrom, ZoneId timeZoneTo) {
        LocalDateTime localDateTime = parseDateTime(dateWithTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, timeZoneFrom).withZoneSameInstant(timeZoneTo);
        return dateTimeFormatter.format(zonedDateTime);
    }

}
