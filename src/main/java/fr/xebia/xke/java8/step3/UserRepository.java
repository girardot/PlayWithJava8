package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.domain.User;
import fr.xebia.xke.java8.step1.DateUtils;
import fr.xebia.xke.java8.tools.UserParser;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = UserParser.fromCsv("users.csv");

    }

    public UserRepository(List<User> users) {
        this.users = users;
    }

    public boolean isUserWithEmptyPassword() {
        return users.stream().anyMatch(u -> u.userWithoutPassword());
    }

    public List<User> findUsersWithEmptyPassword() {
        return users.stream().
                filter(User::userWithoutPassword).
                collect(Collectors.toList());
    }

    public List<User> findActiveUser() {
        LocalDate now = LocalDate.now();
        return users.stream().filter(u -> u.startDateIsPast(now))
                .filter(u -> u.endDateIsNotPast(now))
                .sorted((user1, user2) -> user1.startDate.compareTo(user2.startDate))
                .collect(Collectors.toList());
    }

    public List<User> findAll() {
        return users;
    }

    public int averageAge() {
        return users.stream().
                collect(Collectors.averagingInt(u -> DateUtils.age(u.birthday, LocalDate.now())))
                .intValue();
    }
}
