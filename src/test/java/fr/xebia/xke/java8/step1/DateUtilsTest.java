package fr.xebia.xke.java8.step1;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static fr.xebia.xke.test.TemporalAccessorAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;


public class DateUtilsTest {

    @Test
    public void should_parse_date() {

        assertThat(DateUtils.parseDate("27/01/2014")).isInSameDayAs("2014-01-27");
    }

    @Test
    public void should_parse_date_time() {
        LocalDateTime date = DateUtils.parseDateTime("27/01/2014 12:05:10");

        assertThat(date).isInSameDayAs("2014-01-27");
        assertThat(date).isWithinHourOfDay(12);
        assertThat(date).isWithinMinute(05);
        assertThat(date).isWithinSecond(10);
    }

    @Test
    public void should_compute_age() {
        assertThat(computeAgeFor("2013-07-08", "2014-02-06")).isEqualTo(0);
        assertThat(computeAgeFor("2010-10-12", "2014-02-06")).isEqualTo(3);
        assertThat(computeAgeFor("2010-01-28", "2014-02-06")).isEqualTo(4);
    }

    private int computeAgeFor(String birthday, String currentDate) {
        //TODO:Change parseDateJava8 to parseDateJava8 for switch to localDate

        return DateUtils.age(parseDateJava8(birthday), parseDateJava8(currentDate));
    }

    @Test
    public void should_compute_day_with_time() {
        //TODO:Change parseDateJava8 to parseDateJava8 for switch to localDate
        LocalDateTime dateWithTime = DateUtils.dayDateWithTime(parseDateJava8("2013-07-08"), 15, 12, 3);

        assertThat(dateWithTime).isInSameDayAs("2013-07-08");
        assertThat(dateWithTime).isWithinHourOfDay(15);
        assertThat(dateWithTime).isWithinMinute(12);
        assertThat(dateWithTime).isWithinSecond(3);
    }

    @Test
    public void should_add_duration() {
        //TODO:Change parseDateTimeJava8 to parseDateTimeJava8 for switch to localDate
        LocalDateTime date = DateUtils.addDuration(parseDateTimeJava8("2014-01-27T12:05:10."), 162);

        assertThat(date).isInSameDayAs("2014-01-27");
        assertThat(date).isWithinHourOfDay(14);
        assertThat(date).isWithinMinute(47);
        assertThat(date).isWithinSecond(10);
    }

    @Test
    public void should_return_true_when_days_are_equals() {
        //TODO:Change parseDateTimeJava8 to parseDateTimeJava8 for switch to localDate
        assertThat(DateUtils.dayAreEquals(parseDateTimeJava8("2014-01-27T12:05:10."), parseDateTimeJava8("2014-01-27T20:05:10."))).isTrue();
        assertThat(DateUtils.dayAreEquals(parseDateTimeJava8("2014-01-27T12:05:10."), parseDateTimeJava8("2014-01-28T12:05:10."))).isFalse();
    }

    @Test
    public void should_convert_to_time_zone() {
        String date = DateUtils.convertToTimeZone("18/01/2014 12:00:00.", ZoneId.of("Europe/Moscow"), ZoneId.of("America/New_York"));

        assertThat(date).isEqualTo("18/01/2014 03:00:00");

    }

    @Test
    public void fake_test() {
        assertThat(LocalDate.now()).isInSameDayAs(LocalDate.now().format(DateTimeFormatter.ISO_DATE));

    }

    private LocalDate parseDateJava8(String date) {
        return LocalDate.parse(date);
    }

    private Date parseDateJava7(String java7Date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(java7Date);
        } catch (ParseException e) {
            return null;
        }
    }

    private LocalDateTime parseDateTimeJava8(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }

    private Date parseDateTimeJava7(String dateTime) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.").parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
    }
}
