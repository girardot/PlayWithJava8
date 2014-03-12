package fr.xebia.xke.java8.step2;

import fr.xebia.xke.java8.data.User;
import org.fest.assertions.core.Condition;
import org.fest.assertions.data.MapEntry;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class BasicCollectionOperationsTest {

    @Test
    public void should_empty_password() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("M", "Eddard", "Stark").withPassword("1234").withExpireDate(LocalDate.of(2018, 12, 1)));
        users.add(new User("M", "Eddard", "Robb").withPassword("456"));
        users.add(new User("Me", "Eddard", "Arya").withPassword("789").withExpireDate(LocalDate.of(2012, 12, 1)));
        users.add(new User("M", "Snow", "Jon").withPassword("1011").withExpireDate(LocalDate.of(2011, 12, 1)));

        //when
        BasicCollectionOperations.resetPassword(users);

        //then
        assertThat(users).doNotHave(userWithPassword());

    }

    @Test
    public void should_remove_expired_user() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("M", "Eddard", "Stark").withExpireDate(LocalDate.of(2010, 1, 1)));
        users.add(new User("M", "Eddard", "Robb"));
        users.add(new User("Me", "Eddard", "Arya"));
        users.add(new User("M", "Snow", "Jon"));

        //when
        BasicCollectionOperations.removeExpiredUsers(users);

        //then
        assertThat(users).hasSize(3);
        assertThat(users).doNotHave(userExpired());
    }

    @Test
    public void should_replace_all_date_by_date_plus_one_day() {
        List<LocalDate> localDates = new ArrayList<>();
        localDates.add(LocalDate.of(2014, 2, 10));
        localDates.add(LocalDate.of(2014, 2, 15));
        localDates.add(LocalDate.of(2014, 2, 18));


        BasicCollectionOperations.addOneDayToDates(localDates);

        assertThat(localDates).containsExactly(LocalDate.of(2014, 2, 11), LocalDate.of(2014, 2, 16), LocalDate.of(2014, 2, 19));

    }

    @Test
    public void should_count_words() {
        List<String> words = new ArrayList<>();
        words.add("java");
        words.add("javascript");
        words.add("java");
        words.add("scala");
        words.add("java");
        words.add("scala");

        Map<String, Integer> wordCount = BasicCollectionOperations.countWord(words);

        assertThat(wordCount).contains(
                MapEntry.entry("java", 3),
                MapEntry.entry("javascript", 1),
                MapEntry.entry("scala", 2));

    }

    private Condition<User> userExpired() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return user.isExpired();
            }
        };
    }

    private Condition<User> userWithPassword() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return user.password != null;
            }
        };
    }
}
