package fr.xebia.xke.tools.java7;

import fr.xebia.xke.java7.tools.UserParser;
import org.junit.Test;

import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserParserTest {

    @Test
    public void should_return_list_of_users() {
        List<fr.xebia.xke.java7.domain.User> users = UserParser.fromCsv("users.csv");

        assertThat(users).isNotEmpty();
        assertThat(users) .hasSize(1000);

    }
}
