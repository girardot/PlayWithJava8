package fr.xebia.xke.java8.step4;


import javax.annotation.Nullable;
import java.util.*;

public class NumberUtils {

    public static int[] generateRandom(int number, @Nullable Long seed) {
        //TODO:Replace by Random.ints
        int[] randomValues = new int[number];

        Random random = getRandom(seed);

        for (int i = 0; i < number; i++) {
            randomValues[i] = random.nextInt(number * 10);
        }

        return randomValues;
    }

    public static Map<Boolean, List<Integer>> splitEvenAndOddNumber(int[] numbers) {
        //TODO: Use Collectors.partitioningBy

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
