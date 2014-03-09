package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserService {

    private static final String DEFAULT_FORMATED_ADDRESS = "1 rue de Rivoli\n75001 Paris";
    private List<User> users;

    public UserService() {
        users = UserParser.fromCsv("users.csv");

    }

    public UserService(List<User> users) {
        this.users = users;
    }

    public long countUserWithRole(Role role) {
        long count = 0;
        for (User user : users) {
            if (user.getRole() == role) {
                count++;
            }
        }

        return count;
    }

    public boolean isLoginAlreadyExist(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    public String retrieveFormatedUserAddressByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                if (user.getAddress() != null) {
                    return user.getAddress().formatForEnveloppe();
                }
            }
        }

        return DEFAULT_FORMATED_ADDRESS;
    }

    /**
     * Return a copy of users list ordered by lastname and firstname
     *
     * @return
     */
    public List<User> findAll() {
        List<User> usersOrdered = new ArrayList<>(users.size());
        usersOrdered.addAll(users);

        Collections.sort(usersOrdered, new UserComparator());

        return usersOrdered;
    }

    private static class UserComparator implements Comparator<User> {

        public int compare(User userLeft, User userRight) {
            int lastNameComparaison = userLeft.getLastname().compareTo(userRight.getLastname());
            if (lastNameComparaison == 0) {
                return userLeft.getFirstname().compareTo(userRight.getFirstname());
            } else {

                return lastNameComparaison;
            }
        }
    }
}
