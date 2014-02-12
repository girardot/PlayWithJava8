package fr.xebia.xke.java7.domain;

import fr.xebia.xke.domain.Address;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    public String firstname;

    public String lastname;

    public String login;

    public String password;

    public Date startDate;

    public Date endDate;

    public Date birthday;

    public Address billingAddress;

    public Address deliveryAddress;


    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public boolean endDateIsNotPast(Date now) {
        return (endDate == null || endDate.after(now));
    }

    public boolean startDateIsPast(Date now) {
        return startDate.before(now);
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

    public User withbirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public User withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public User withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public User withBillingAddress(Address address) {
        this.billingAddress = address;
        return this;
    }

    public User withDeliveryAddress(Address address) {
        this.deliveryAddress = address;
        return this;
    }


    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", startDate=" + simpleDateFormat.format(startDate) +
                ", endDate=" + simpleDateFormat.format(endDate) +
                ", birthday=" + simpleDateFormat.format(birthday) +
                ", billingAddress=" + billingAddress +
                ", deliveryAddress=" + deliveryAddress +
                '}';
    }
}
