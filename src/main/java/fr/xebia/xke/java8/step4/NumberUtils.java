package fr.xebia.xke.java8.step4;

import com.sun.istack.internal.Nullable;

import java.util.*;

public class NumberUtils {

    public static int[] generateRandom(int number, @Nullable Long seed) {
        return getRandom(seed).
                ints(0, number * 10).
                limit(number).
                toArray();
    }

    public static Map<Boolean, List<Integer>> splitEvenAndOddNumber(int[] numbers) {
        List<Integer> evenNumber = new ArrayList<>();
        List<Integer> oddNumber = new ArrayList<>();

        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumber.add(number);
            } else {
                oddNumber.add(number);
            }
        }

        Map<Boolean, List<Integer>> result = new HashMap<>();
        result.put(Boolean.TRUE, evenNumber);
        result.put(Boolean.FALSE, oddNumber);

        return result;
    }

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
