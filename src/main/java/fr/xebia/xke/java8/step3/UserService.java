package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

import java.util.*;

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
        //TODO: use filter and count
        long count = 0;
        for (User user : users) {
            if (user.getRole() == role) {
                count++;
            }
        }

        return count;
    }

    public boolean isLoginAlreadyExist(String login) {
        //TODO: use anyMatch
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    public String retrieveFormatedUserAddressByLogin(String login) {
        //TODO: user filter and findFirst. Replace user.address type by Optional
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
        //TODO: use sorted without specific comparator class creation. Use Comparator methods and Collectors
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

    public Map<Role, List<User>> retrieveActiveUserByRole() {
        //TODO: Use Collectors.groupingBy
        Map<Role, List<User>> result = new HashMap<>();

        for (User user : users) {
            if (!user.isExpired()) {

                List<User> currentRoleUsers = result.get(user.getRole());
                if (currentRoleUsers == null) {
                    currentRoleUsers = new ArrayList<>();
                    result.put(user.getRole(), currentRoleUsers);
                }
                currentRoleUsers.add(user);
            }
        }

        return result;
    }

    public Map<String, User> retrieveUserwithRoleByLogin(Role role) {
        //TODO: Use Collectors.toMap
        Map<String, User> result = new HashMap<>();

        for (User user : users) {
            if (user.getRole() == role) {
                result.put(user.getLogin(), user);
            }
        }

        return result;
    }
}