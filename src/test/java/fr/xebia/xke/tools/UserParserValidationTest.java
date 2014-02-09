package fr.xebia.xke.tools;

import fr.xebia.xke.java7.tools.UserParser;
import fr.xebia.xke.test.Conditions;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserParserValidationTest {

    @Test
    public void should_return_list_of_users() {
        assertThat(UserParser.fromCsv("users.csv")).
                is(Conditions.equivalentAs(fr.xebia.xke.java8.tools.UserParser.fromCsv("users.csv")));

    }
}
