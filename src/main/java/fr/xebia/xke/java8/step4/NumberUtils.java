package fr.xebia.xke.java8.step4;

import com.sun.istack.internal.Nullable;

import java.util.Random;

public class NumberUtils {

    public static int[] generateRandom(int number, @Nullable Long seed) {
        int[] randomValues = new int[number];

        Random random = getRandom(seed);

        for (int i = 0; i < number; i++) {
            randomValues[i] = random.nextInt(number * 10);
        }

        return randomValues;
    }

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
