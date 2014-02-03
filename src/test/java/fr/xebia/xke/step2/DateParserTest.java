package fr.xebia.xke.step2;

import fr.xebia.xke.java8.step2.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import static org.fest.assertions.api.Assertions.assertThat;

public class DateParserTest {

    @Test
    public void should_parse_date() {
        String date = "1996-09-24T01:50:39.";

        Date dateResult = fr.xebia.xke.java7.step2.DateUtils.parseDateTime(date);
        assertThat(dateResult).isInSameDayAs("1996-09-24");
        assertThat(dateResult).isWithinHourOfDay(1);
        assertThat(dateResult).isWithinMinute(50);
        assertThat(dateResult).isWithinSecond(39);

        assertThat(DateUtils.parseDateTime(date).toInstant(ZoneOffset.of("+2"))).isEqualTo(fr.xebia.xke.java7.step2.DateUtils.parseDateTime(date).toInstant());

    }

    @Test
    public void should_compute_age() throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        assertThat(DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2011-01-01"))).isEqualTo(fr.xebia.xke.java7.step2.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2011-01-01")));
        assertThat(DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2011-12-31"))).isEqualTo(fr.xebia.xke.java7.step2.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2011-12-31")));
        assertThat(DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2010-12-31"))).isEqualTo(fr.xebia.xke.java7.step2.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2010-12-31")));
        assertThat(DateUtils.age(LocalDate.parse("2010-01-01"), LocalDate.parse("2011-05-12"))).isEqualTo(fr.xebia.xke.java7.step2.DateUtils.age(parser.parse("2010-01-01"), parser.parse("2011-05-12")));
    }
}
