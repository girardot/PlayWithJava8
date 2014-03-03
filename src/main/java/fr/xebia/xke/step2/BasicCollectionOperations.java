package fr.xebia.xke.step2;

import fr.xebia.xke.fr.xebia.xke.java8.data.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicCollectionOperations {

    public static void resetPassword(List<User> users) {
        //TODO :Refactor with new Iterable Method

        users.forEach(user -> user.password = null);
    }

    public static void removeExpiredUsers(List<User> users) {
        //TODO :Refactor with new Collection Method

        users.removeIf(User::isExpired);
    }

    public static void addOneDayToDates(List<LocalDate> localDates) {
        //TODO :Refactor with new List Method

        localDates.replaceAll(date -> date.plusDays(1));
    }

    public static Map<String, Integer> countWord(List<String> words) {
        //TODO :Refactor Map computation with new map method and you can eventually change loop by iterable method
        Map<String, Integer> count = new HashMap<>();

        words.forEach(word -> count.merge(word, 1, (oldValue, newValue) -> oldValue + newValue));

        return count;
    }

}
