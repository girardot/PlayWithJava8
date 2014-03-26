package fr.xebia.xke.java8.step4;

import com.sun.istack.internal.Nullable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumberUtils {

    private static Map<Integer, Long> fibonacciValues = new HashMap<>();

    static {
        fibonacciValues.put(0, 0L);
        fibonacciValues.put(1, 1L);
        fibonacciValues.put(2, 1L);
    }

    public static int[] generateRandom(int number, @Nullable Long seed) {
        return getRandom(seed).
                ints(0, number * 10).
                limit(number).
                toArray();
    }

    public static Map<Boolean, List<Integer>> splitEvenAndOddNumber(int[] numbers) {

        return Arrays.stream(numbers).
                mapToObj(Integer::valueOf).
                collect(Collectors.partitioningBy(intValue -> intValue % 2 == 0));
    }

    public static List<Long> fibonacci(int expectedNumberResult) {
        //TODO: replace for by stream generation with IntStream, map and collect

        return IntStream.rangeClosed(1, expectedNumberResult).
                mapToLong(NumberUtils::fibonacciComputation).
                mapToObj(Long::valueOf).
                collect(Collectors.toList());
    }

    private static long fibonacciComputation(int number) {
        return fibonacciValues.computeIfAbsent(number, newNumber -> fibonacciComputation(newNumber - 1) + fibonacciComputation(newNumber - 2));
    }

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
