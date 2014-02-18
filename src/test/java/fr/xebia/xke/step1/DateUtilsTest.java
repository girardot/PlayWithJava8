package fr.xebia.xke.step1;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static fr.xebia.xke.test.Conditions.equivalentAs;
import static org.fest.assertions.api.Assertions.assertThat;

public class DateUtilsTest {

    @Test
    public void should_parse_date() {
        String dateToParse = "2014-01-27";

        assertThat(DateUtilsJava7.parseDate(dateToParse)).is(equivalentAs(DateUtils.parseDate(dateToParse)));
    }

    @Test
    public void should_parse_date_time() {
        String dateTimeToParse = "2014-01-27T12:05:10.";

        assertThat(DateUtilsJava7.parseDateTime(dateTimeToParse)).is(equivalentAs(DateUtils.parseDateTime(dateTimeToParse)));

    }

    @Test
    public void should_compute_age() throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        checkAgeMethod("2010-01-01", "2011-01-01");
        checkAgeMethod("2010-01-01", "2011-12-31");
        checkAgeMethod("2010-01-01", "2010-12-31");
        checkAgeMethod("2010-01-01", "2011-05-12");
    }

    private void checkAgeMethod(String birthday, String now) {
        assertThat(DateUtilsJava7.age(DateUtilsJava7.parseDate(birthday), DateUtilsJava7.parseDate(now))).isEqualTo(DateUtils.age(DateUtils.parseDate(birthday), DateUtils.parseDate(now)));
    }

    @Test
    public void should_compute_day_with_time() {
        String date = "2013-07-08";

        assertThat(DateUtilsJava7.dayDateWithTime(DateUtilsJava7.parseDate(date), 15, 12, 3)).is(equivalentAs(DateUtils.dayDateWithTime(DateUtils.parseDate(date), 15, 12, 3)));
    }

    @Test
    public void should_add_duration() {
        String firstDate = "2014-01-27T12:05:10.";

        assertThat(DateUtilsJava7.addDuration(DateUtilsJava7.parseDateTime(firstDate), 162)).is(equivalentAs(DateUtils.addDuration(DateUtils.parseDateTime(firstDate), 162)));
    }

    @Test
    public void should_return_true_when_days_are_equals() {

        String firstDate = "2014-01-27T12:05:10.";
        String secondDate = "2014-01-27T18:05:10.";
        String thirdDate = "2014-01-28T18:05:10.";

        assertThat(DateUtilsJava7.dayAreEquals(firstDate, secondDate)).isEqualTo(DateUtils.dayAreEquals(firstDate, secondDate));
        assertThat(DateUtilsJava7.dayAreEquals(firstDate, thirdDate)).isEqualTo(DateUtils.dayAreEquals(firstDate, thirdDate));
    }
}
