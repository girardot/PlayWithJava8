package fr.xebia.xke.java8.step4;


import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberUtils {

    private static Map<Integer, Long> fibonacciValues = new HashMap<>();

    static {
        fibonacciValues.put(0, 0L);
        fibonacciValues.put(1, 1L);
        fibonacciValues.put(2, 1L);
    }

    /**
     * Return the 'number' random int between 0 and 'number * 10'
     *
     * @param number
     * @param seed
     * @return int[]
     */
    public static int[] generateRandom(int number, @Nullable Long seed) {
        return getRandom(seed).ints(number, 0, number * 10).toArray();
    }

    public static Map<Boolean, List<Integer>> splitEvenAndOddNumber(int[] numbers) {
        return Arrays.stream(numbers).mapToObj(Integer::valueOf).collect(Collectors.partitioningBy(i -> (i % 2 == 0)));
    }

    public static List<Long> fibonacci(int expectedNumberResult) {
        return IntStream.rangeClosed(1, expectedNumberResult).mapToObj(Integer::valueOf).map(NumberUtils::fibonacciComputation).collect(Collectors.toList());
    }

    /**
     * @return Infinite stream who return each next number of fibonacci sequence
     */
    public static Stream<Long> fibonacciStream() {
        return Stream.generate(new fibonacciGenerator());
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

    public static class fibonacciGenerator implements Supplier<Long> {

        private AtomicInteger number = new AtomicInteger(1);

        @Override
        public Long get() {
            return fibonacciComputation(number.getAndIncrement());
        }
    }

}
