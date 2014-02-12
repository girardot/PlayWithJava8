package fr.xebia.xke.step3;

import fr.xebia.xke.java8.step3.UserRepository;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserRepositoryValidationTest {

    fr.xebia.xke.java7.step3.UserRepository userRepositoryJava7 = new fr.xebia.xke.java7.step3.UserRepository();
    UserRepository userRepositoryJava8 = new UserRepository();

    @Test
    public void should_return_true_when_user_with_empty_password_exist() {
        assertThat(userRepositoryJava7.isUserWithEmptyPassword()).isFalse();
        assertThat(userRepositoryJava8.isUserWithEmptyPassword()).isFalse();

        removePasswordOfFirstUser(userRepositoryJava7, userRepositoryJava8);

        assertThat(userRepositoryJava7.isUserWithEmptyPassword()).isTrue();
        assertThat(userRepositoryJava8.isUserWithEmptyPassword()).isTrue();
    }

    @Test
    public void should_return_user_with_empty_password() {
        removePasswordOfFirstUser(userRepositoryJava7, userRepositoryJava8);

        //When  //Then
        assertThat(userRepositoryJava7.findUsersWithEmptyPassword().toString()).isEqualTo(userRepositoryJava8.findUsersWithEmptyPassword().toString());

    }

    @Test
    public void should_return_all_active_users() {
        assertThat(userRepositoryJava7.findActiveUser().toString()).isEqualTo(userRepositoryJava8.findActiveUser().toString());
    }

    @Test
    public void should_return_age_average() {
        assertThat(userRepositoryJava7.averageAge()).isEqualTo(userRepositoryJava8.averageAge());
    }

    private void removePasswordOfFirstUser(fr.xebia.xke.java7.step3.UserRepository userRepositoryJava7, UserRepository userRepositoryJava8) {
        userRepositoryJava7.findAll().get(0).password = null;
        userRepositoryJava8.findAll().get(0).password = null;

    }
}
