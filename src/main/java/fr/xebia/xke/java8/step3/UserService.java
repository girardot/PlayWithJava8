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
        long count = 0;
        for (User user : users) {
            if (user.role == role) {
                count++;
            }
        }

        return count;
    }

    public boolean isLoginAlreadyExist(String login) {
        for (User user : users) {
            if (user.login.equals(login)) {
                return true;
            }
        }

        return false;
    }

    public List<User> findAll() {
        return users;
    }
}
