package fr.xebia.xke.java8.step4;

import com.sun.istack.internal.Nullable;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumberUtils {

    public static Integer[] generateUniqueRandom(int number, @Nullable Long seed) {
        Set<Integer> randomLong = new HashSet<>(number);
        Random random = getRandom(seed);
        while (randomLong.size() < number) {
            randomLong.add(random.nextInt(number * 10));
        }

        return randomLong.toArray(new Integer[number]);
    }

    private static Random getRandom(Long seed) {
        if (seed == null) {
            return new Random();
        } else {
            return new Random(seed);
        }
    }
}
