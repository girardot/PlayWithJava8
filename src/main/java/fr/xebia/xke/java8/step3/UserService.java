package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Address;
import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

import java.util.Comparator;
import java.util.List;
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
}
