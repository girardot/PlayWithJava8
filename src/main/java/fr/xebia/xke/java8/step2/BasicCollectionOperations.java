package fr.xebia.xke.java8.step2;

import fr.xebia.xke.java8.data.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCollectionOperations {

    public static void resetPassword(List<User> users) {
        //TODO :Refactor with forEach

        for (User user : users) {
            user.password = null;
        }
    }

    public static void removeExpiredUsers(List<User> users) {
        //TODO :Refactor with removeIf, use method reference

        List<User> usersToRemove = new ArrayList<>();

        for (User user : users) {
            if (user.isExpired()) {
                usersToRemove.add(user);
            }
        }

        users.removeAll(usersToRemove);
    }

    public static void addOneDayToDates(List<LocalDate> localDates) {
        //TODO :Refactor with replaceAll

        for (int i = 0; i < localDates.size(); i++) {
            LocalDate localDate = localDates.get(i);
            LocalDate newDate = localDate.plusDays(1);

            localDates.set(i, newDate);
        }
    }

    public static Map<String, Integer> countWord(List<String> words) {
        //TODO :Refactor Map computation with merge and you can eventually change loop by forEach method
        Map<String, Integer> count = new HashMap<>();

        for (String word : words) {
            Integer countOfCurrentWord = count.get(word);
            if (countOfCurrentWord == null) {
                countOfCurrentWord = 0;
            }

            count.put(word, countOfCurrentWord + 1);
        }

        return count;
    }

}
