package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

import java.util.List;

public class UserService {

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

    public List<User> findAll() {
        return users;
    }
}
