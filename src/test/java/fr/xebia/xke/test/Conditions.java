package fr.xebia.xke.test;

import org.fest.assertions.core.Condition;

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

    public static <L, V> Condition<? super L> equivalentAs(final V object) {
        return new Condition<L>() {
            @Override
            public boolean matches(L value) {

                return value.toString().equals(object.toString());
            }
        };

    }
}
