package fr.xebia.xke.java8.step3;

import fr.xebia.xke.domain.Address;
import fr.xebia.xke.java8.domain.User;
import fr.xebia.xke.java8.step1.DateUtils;
import fr.xebia.xke.java8.tools.UserParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    public int averageAge() {
        return users.stream().
                collect(Collectors.averagingInt(u -> DateUtils.age(u.birthday, LocalDate.now())))
                .intValue();
    }

    public String adressForDelivery(User user) {
        return Optional.ofNullable(user.deliveryAddress)
                .map(address -> formatAddressForDelivery(user, address))
                .orElse(formatAddressForDelivery(user, user.billingAddress));
    }

    public String formatAddressForDelivery(fr.xebia.xke.java8.domain.User user, Address address) {
        return String.format("%s %s\n%s\n%s %s", user.firstname, user.lastname, address.street, address.postalCode, address.city);
    }

    public List<User> findAll() {
        return users;
    }
}
