package fr.xebia.xke.java8.step3;

import fr.xebia.xke.java8.data.Role;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void should_return_count_of_Role() {
        assertThat(userService.countUserWithRole(Role.SALES)).isEqualTo(322);
        assertThat(userService.countUserWithRole(Role.ENGINEER)).isEqualTo(315);
        assertThat(userService.countUserWithRole(Role.TRADER)).isEqualTo(363);
    }

    @Test
    public void should_return_true_if_login_exist() {
        assertThat(userService.isLoginAlreadyExist("cmoreau")).isTrue();
        assertThat(userService.isLoginAlreadyExist("ibeauvais")).isFalse();

    }
}
