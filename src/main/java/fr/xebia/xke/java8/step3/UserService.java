package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Address;
import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import fr.xebia.xke.java8.other.UserParser;

import java.util.*;
import java.util.function.Function;
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
        return users.stream().filter(u -> u.getRole() == role).count();
    }

    public boolean isLoginAlreadyExist(String login) {
        return users.stream().anyMatch(u -> u.getLogin().equals(login));
    }

    public String retrieveFormatedUserAddressByLogin(String login) {
        Optional<String> stringOptional = users.stream().filter(u -> u.getLogin().equals(login)).filter(u -> u.getAddress() != null).findFirst().flatMap(User::getAddress).map(Address::formatForEnveloppe);
        return stringOptional.orElse(DEFAULT_FORMATED_ADDRESS);
    }

    /**
     * Return a copy of users list ordered by lastname and firstname
     *
     * @return List<User>
     */
    public List<User> findAll() {
        return users.stream().sorted(Comparator.comparing(User::getLastname).thenComparing(User::getFirstname)).collect(Collectors.toList());
    }

    public Map<Role, List<User>> retrieveActiveUserByRole() {
        return users.stream().filter(u -> !u.isExpired()).collect(Collectors.groupingBy(User::getRole));
    }

    public Map<String, User> retrieveUserwithRoleByLogin(Role role) {
        return users.stream().filter(u -> u.getRole() == role).collect(Collectors.toMap(User::getLogin, Function.identity()));
    }

    public String generateAgeStatistic() {
        IntSummaryStatistics intSummaryStatistics = users.stream().collect(Collectors.summarizingInt(User::age));
        return String.format("Number of user : %d\nAge min : %d\nAge max : %d\nAge average : %.2f", intSummaryStatistics.getCount(), intSummaryStatistics.getMin(), intSummaryStatistics.getMax(), intSummaryStatistics.getAverage());
    }

}
