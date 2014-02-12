package fr.xebia.xke.java7.step3;

import fr.xebia.xke.domain.Address;
import fr.xebia.xke.java7.domain.User;
import fr.xebia.xke.java7.step1.DateUtils;
import fr.xebia.xke.java7.tools.UserParser;

import java.util.*;

public class UserRepository {

    private List<User> users;

    public UserRepository() {
        users = UserParser.fromCsv("users.csv");

    }

    public UserRepository(List<User> users) {
        this.users = users;
    }

    public boolean isUserWithEmptyPassword() {
        for (User user : users) {
            if (user.userWithoutPassword()) {
                return true;
            }
        }

        return false;
    }

    public List<User> findUsersWithEmptyPassword() {
        List<User> usersWithoutPassword = new ArrayList<>();
        for (User user : users) {
            if (user.userWithoutPassword()) {
                usersWithoutPassword.add(user);
            }
        }

        return usersWithoutPassword;
    }

    public List<User> findActiveUser() {
        List<User> activeUsers = new ArrayList<>();

        Date now = new Date();

        for (User user : users) {
            if (user.startDateIsPast(now) && user.endDateIsNotPast(now)) {
                activeUsers.add(user);
            }
        }

        Collections.sort(activeUsers, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.startDate.compareTo(user2.startDate);
            }
        });

        return activeUsers;
    }

    public int averageAge() {
        int sumOfAge = 0;

        Date now = new Date();

        for (User user : users) {
            sumOfAge += DateUtils.age(user.birthday, now);
        }

        return sumOfAge / users.size();
    }

    public String adressForDelivery(User user) {
        if (user.deliveryAddress != null) {
            return formatAddressForDelivery(user, user.deliveryAddress);
        } else {
            return formatAddressForDelivery(user, user.billingAddress);
        }
    }

    public String formatAddressForDelivery(User user, Address address) {
        return String.format("%s %s\n%s\n%s %s", user.firstname, user.lastname, address.street, address.postalCode, address.city);
    }

    public List<User> findAll() {
        return users;
    }
}
