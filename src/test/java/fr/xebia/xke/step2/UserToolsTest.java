package fr.xebia.xke.step2;

import fr.xebia.xke.fr.xebia.xke.java8.data.User;
import org.fest.assertions.core.Condition;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserToolsTest {

    @Test
    public void should_empty_password_when_users_are_expired() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("M", "Eddard", "Stark").withPassword("1234").withExpireDate(LocalDate.of(2018, 12, 1)));
        users.add(new User("M", "Eddard", "Robb").withPassword("456"));
        users.add(new User("Me", "Eddard", "Arya").withPassword("789").withExpireDate(LocalDate.of(2012, 12, 1)));
        users.add(new User("M", "Snow", "Jon").withPassword("1011").withExpireDate(LocalDate.of(2011, 12, 1)));

        //when
        UserTools.resetExpiredUsersPassword(users);

        //then
        assertThat(users).doNotHave(userExpiredWithPassword());

    }

    @Test
    public void should_remove_user_with_state_to_delete() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("M", "Eddard", "Stark").delete());
        users.add(new User("M", "Eddard", "Robb"));
        users.add(new User("Me", "Eddard", "Arya"));
        users.add(new User("M", "Snow", "Jon"));

        //when
        UserTools.removeUsersToDelete(users);

        //then
        assertThat(users).hasSize(3);
        assertThat(users).doNotHave(userToDelete());
    }

    private Condition<User> userToDelete() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return user.toDelete;
            }
        };
    }

    private Condition<User> userExpiredWithPassword() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return user.isExpired() && user.password != null;
            }
        };
    }
}
