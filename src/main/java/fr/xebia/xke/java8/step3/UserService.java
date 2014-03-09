package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Address;
import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
        return users.stream().
                filter(user -> user.getRole() == role).
                count();
    }

    public boolean isLoginAlreadyExist(String login) {
        return users.stream().anyMatch(user -> user.getLogin().equals(login));
    }

    public String retrieveFormatedUserAddressByLogin(String login) {
        return users.stream().
                filter(user -> user.getLogin().equals(login)).
                findFirst().
                flatMap(user -> user.getAddress()).
                map(Address::formatForEnveloppe)
                .orElse(DEFAULT_FORMATED_ADDRESS);

    }

    /**
     * Return a copy of users list ordered by lastname and firstname
     *
     * @return
     */
    public List<User> findAll() {
        return users.stream().
                sorted(
                        Comparator.comparing(User::getLastname).
                                thenComparing(User::getFirstname)
                ).
                collect(Collectors.toList());
    }

    public Map<Role, List<User>> retrieveActiveUserByRole() {
        Map<Role, List<User>> result = new HashMap<>();

        for (User user : users) {
            if (user.getExpireDate() == null || user.getExpireDate().isAfter(LocalDate.now())) {

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
}
