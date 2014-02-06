package fr.xebia.xke.java7.step1;

import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void should_parse_date_time() {
        Date date = DateUtils.parseDateTime("2014-01-27T12:05:10.");

        assertThat(date).isInSameDayAs("2014-01-27");
        assertThat(date).isWithinHourOfDay(12);
        assertThat(date).isWithinMinute(05);
        assertThat(date).isWithinSecond(10);
    }

    @Test
    public void should_parse_date() {
        Date date = DateUtils.parseDate("2014-01-27");

        assertThat(date).isInSameDayAs("2014-01-27");
    }

    @Test
    public void should_compute_age() {
        assertThat(DateUtils.age(DateUtils.parseDate("2013-07-08"), DateUtils.parseDate("2014-02-06"))).isEqualTo(0);
        assertThat(DateUtils.age(DateUtils.parseDate("2010-10-12"), DateUtils.parseDate("2014-02-06"))).isEqualTo(3);
        assertThat(DateUtils.age(DateUtils.parseDate("2010-01-28"), DateUtils.parseDate("2014-02-06"))).isEqualTo(4);
    }

    @Test
    public void should_compute_day_with_time() {
        Date day = DateUtils.parseDate("2013-07-08");
        Date dateWithTime = DateUtils.dayDateWithTime(day, 15, 12, 3);

        assertThat(dateWithTime).isInSameDayAs("2013-07-08");
        assertThat(dateWithTime).isWithinHourOfDay(15);
        assertThat(dateWithTime).isWithinMinute(12);
        assertThat(dateWithTime).isWithinSecond(3);
    }
}
