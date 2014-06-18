package fr.xebia.xke.java8.step2;

import fr.xebia.xke.java8.data.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCollectionOperations {

    public static void resetPassword(List<User> users) {
        users.forEach(user -> user.password = null);
    }

    public static void removeExpiredUsers(List<User> users) {
        users.removeIf(User::isExpired);
    }

    public static void addOneDayToDates(List<LocalDate> localDates) {
        localDates.replaceAll(localDate -> localDate.plusDays(1));
    }

    public static Map<String, Integer> countWord(List<String> words) {
        Map<String, Integer> count = new HashMap<>();
        words.forEach(word -> count.merge(word, 1, (i, j) -> i + 1));
        return count;
    }

    private static Map<Integer, Long> fibonacciValues = new HashMap<>();

    static {
        fibonacciValues.put(0, 0L);
        fibonacciValues.put(1, 1L);
        fibonacciValues.put(2, 1L);
    }

    public static List<Long> fibonacci(int expectedNumberResult) {
        List<Long> result = new ArrayList<>(expectedNumberResult);

        for (int i = 1; i <= expectedNumberResult; i++) {
            result.add(fibonacciComputation(i));
        }
        return result;
    }

    private static long fibonacciComputation(int number) {
        if (number < 2) {
            return number;
        } else {
            return fibonacciValues.computeIfAbsent(number - 1, BasicCollectionOperations::fibonacciComputation) +
                    fibonacciValues.computeIfAbsent(number - 2, BasicCollectionOperations::fibonacciComputation);
        }
    }

}
