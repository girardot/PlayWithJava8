package fr.xebia.xke.java8.domain;

import java.time.LocalDate;

public class User {

    public String firstname;

    public String lastname;

    public String login;

    public String password;

    public LocalDate startDate;

    public LocalDate endDate;

    public LocalDate birthday;

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean endDateIsNotPast(LocalDate now) {
        return (endDate == null || endDate.isAfter(now));
    }

    public boolean startDateIsPast(LocalDate now) {
        return startDate.isBefore(now);
    }

    public boolean userWithoutPassword() {
        return password == null || password.isEmpty();
    }

    public User withLogin(String login) {
        this.login = login;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public User withbirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public User withStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public User withEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", birthday=" + birthday +
                '}';
    }
}
