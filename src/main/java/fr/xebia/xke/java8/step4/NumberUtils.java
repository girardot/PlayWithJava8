package fr.xebia.xke.java8.step4;

import com.sun.istack.internal.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class NumberUtils {

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

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
