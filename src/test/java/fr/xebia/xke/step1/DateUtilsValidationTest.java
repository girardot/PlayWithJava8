package fr.xebia.xke.step1;

import fr.xebia.xke.java7.step1.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static fr.xebia.xke.test.Conditions.equivalentAs;
import static org.fest.assertions.api.Assertions.assertThat;

public class DateUtilsValidationTest {

    @Test
    public void should_parse_date_time() {
        String dateTimeToParse = "2014-01-27T12:05:10.";

        Date date = DateUtils.parseDateTime(dateTimeToParse);
        LocalDateTime localDateTime = fr.xebia.xke.java8.step1.DateUtils.parseDateTime(dateTimeToParse);

        assertThat(date).is(equivalentAs(localDateTime));

    }

    @Test
    public void should_parse_date() {
        String dateToParse = "2014-01-27";
        Date date = DateUtils.parseDate(dateToParse);
        LocalDate localDate = fr.xebia.xke.java8.step1.DateUtils.parseDate(dateToParse);

        assertThat(date).is(equivalentAs(localDate));
    }


    @Test
    public void should_compute_age() throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(fr.xebia.xke.java8.step1.DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2011-01-01"))).isEqualTo(fr.xebia.xke.java7.step1.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2011-01-01")));
        assertThat(fr.xebia.xke.java8.step1.DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2011-12-31"))).isEqualTo(fr.xebia.xke.java7.step1.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2011-12-31")));
        assertThat(fr.xebia.xke.java8.step1.DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2010-12-31"))).isEqualTo(fr.xebia.xke.java7.step1.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2010-12-31")));
        assertThat(fr.xebia.xke.java8.step1.DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2011-05-12"))).isEqualTo(fr.xebia.xke.java7.step1.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2011-05-12")));
    }

    @Test
    public void should_compute_day_with_time() {
        String date = "2013-07-08";
        Date dateWithTime = DateUtils.dayDateWithTime(DateUtils.parseDate(date), 15, 12, 3);

        LocalDateTime localDateTime = fr.xebia.xke.java8.step1.DateUtils.dayDateWithTime(fr.xebia.xke.java8.step1.DateUtils.parseDate(date), 15, 12, 3);

        assertThat(dateWithTime).is(equivalentAs(localDateTime));
    }

    @Test
    public void should_add_duration() {
        String firstDate = "2014-01-27T12:05:10.";
        Date date = DateUtils.addDuration(DateUtils.parseDateTime(firstDate), 162);
        LocalDateTime localDateTime = fr.xebia.xke.java8.step1.DateUtils.addDuration(fr.xebia.xke.java8.step1.DateUtils.parseDateTime(firstDate), 162);

        assertThat(date).is(equivalentAs(localDateTime));
    }

}
