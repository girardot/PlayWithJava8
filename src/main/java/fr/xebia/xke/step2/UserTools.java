package fr.xebia.xke.step2;

import fr.xebia.xke.fr.xebia.xke.java8.data.User;

import java.util.ArrayList;
import java.util.List;

public class UserTools {

    public static void resetExpiredUsersPassword(List<User> users) {
        for (User user : users) {
            if (user.isExpired())
                user.password = null;
        }
    }

    public static void removeUsersToDelete(List<User> users) {
        List<User> usersToRemove = new ArrayList<>();

        for (User user : users) {
            if (user.toDelete) {
                usersToRemove.add(user);
            }
        }

        users.removeAll(usersToRemove);
    }

}
