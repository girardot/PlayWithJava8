package fr.xebia.xke.test;

import org.fest.assertions.core.Condition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Conditions {

    public static <L, V> Condition<? super List<L>> equivalentAs(final List<V> list) {
        return new Condition<List<L>>() {
            @Override
            public boolean matches(List<L> value) {

                return value.toString().equals(list.toString());
            }
        };

    }

    public static <L, V> Condition<? super L> equivalentAs(V object) {
        return new Condition<L>() {
            @Override
            public boolean matches(L value) {

                return value.toString().equals(object.toString());
            }
        };

    }

    public static  Condition<Date> equivalentAs(Date object) {
        return new Condition<Date>() {
            @Override
            public boolean matches(Date value) {

                return value.equals(object);
            }
        };

    }

    public static Condition<Date> equivalentAs(LocalDateTime localDateTime) {
        return new Condition<Date>() {
            @Override
            public boolean matches(Date date) {
                LocalDateTime localDateFromDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                return localDateFromDate.equals(localDateTime);
            }
        };

    }

    public static Condition<Date> equivalentAs(LocalDate localDate) {
        return new Condition<Date>() {
            @Override
            public boolean matches(Date date) {
                LocalDate localDateFromDate = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
                return localDateFromDate.equals(localDate);
            }
        };

    }
}
