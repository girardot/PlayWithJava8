package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

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
        return users.stream().
                filter(user -> user.role == role).
                count();
    }

    public boolean isLoginAlreadyExist(String login) {
        return users.stream().anyMatch(user -> user.login.equals(login));
    }

    public String retrieveFormatedUserAddressByLogin(String login) {
        for (User user : users) {
            if (user.login.equals(login)) {
                if (user.address != null) {
                    return user.address.formatForEnveloppe();
                } else {
                    return DEFAULT_FORMATED_ADDRESS;
                }
            }
        }

        throw new IllegalArgumentException("User not found with login : " + login);
    }

    public List<User> findAll() {
        return users;
    }
}
