package fr.xebia.xke.java8.data;

import java.time.LocalDate;
import java.util.Optional;

public class User {

    public final String title;

    public final String firstname;

    public final String lastname;

    public String login;

    public String password;

    public LocalDate expireDate;

    public Role role;

    public Optional<Address> address = Optional.empty();

    public User(String title, String firstname, String lastname) {
        this.title = title;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = firstname + "." + lastname;
    }

    public boolean isExpired() {
        return expireDate != null && expireDate.isBefore(LocalDate.now());
    }

    public User withLogin(String login) {
        this.login = login;
        return this;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    public User withExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
        return this;
    }

    public User withRole(Role role) {
        this.role = role;
        return this;
    }

    public User withAddress(Address address) {
        this.address = Optional.ofNullable(address);
        return this;
    }

}
