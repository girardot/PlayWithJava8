package fr.xebia.xke.java8.step4;

import com.sun.istack.internal.Nullable;

import java.util.Random;

public class NumberUtils {

    public static int[] generateRandom(int number, @Nullable Long seed) {
        return getRandom(seed).
                ints(0, number * 10).
                limit(number).
                toArray();
    }

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
