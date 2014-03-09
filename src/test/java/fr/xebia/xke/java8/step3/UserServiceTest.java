package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void should_return_count_of_Role() {
        assertThat(userService.countUserWithRole(Role.SALES)).isEqualTo(325);
        assertThat(userService.countUserWithRole(Role.ENGINEER)).isEqualTo(336);
        assertThat(userService.countUserWithRole(Role.TRADER)).isEqualTo(339);
    }

    @Test
    public void should_return_true_if_login_exist() {
        assertThat(userService.isLoginAlreadyExist("oliviermichel")).isTrue();
        assertThat(userService.isLoginAlreadyExist("ivanbeauvais")).isFalse();

    }

    @Test
    public void should_return_formated_address_when_user_with_address_exist() {
        assertThat(userService.retrieveFormatedUserAddressByLogin("oliviermichel")).isEqualTo("33 Birch Street\nJUNTURA 97911");
    }

    @Test
    public void should_return_formated_address_when_user_without_address_exist() {
        assertThat(userService.retrieveFormatedUserAddressByLogin("julielaurent")).isEqualTo("1 rue de Rivoli\n75001 Paris");
    }

    @Test
    public void should_return_throw_exception_when_user_not_exist() {
        assertThat(userService.retrieveFormatedUserAddressByLogin("ivanbeauvais")).isEqualTo("1 rue de Rivoli\n75001 Paris");
    }

    @Test
    public void should_return_list_ordered_by_firstname_and_lastname() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("Mme", "Martine", "Mercier"));
        users.add(new User("M.", "Christophe", "Mercier"));
        users.add(new User("Mme", "Pascal", "Dubois"));

        UserService userService = new UserService(users);

        //when
        List<User> usersOrdered = userService.findAll();

        //Then
        assertThat(usersOrdered.toString()).isEqualTo("[User{title='Mme', firstname='Pascal', lastname='Dubois'}, User{title='M.', firstname='Christophe', lastname='Mercier'}, User{title='Mme', firstname='Martine', lastname='Mercier'}]");
    }

}
