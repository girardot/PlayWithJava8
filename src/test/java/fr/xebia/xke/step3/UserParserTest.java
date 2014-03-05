package fr.xebia.xke.step3;

import fr.xebia.xke.fr.xebia.xke.java8.data.User;
import org.junit.Test;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserParserTest {

    @Test
    public void should_parse_user_csv() {
        List<User> users = UserParser.fromCsv("users.csv");

        assertThat(users).hasSize(1000);
        User firstUser = users.get(0);
        assertThat(firstUser.title).isEqualTo("M.");
        assertThat(firstUser.firstname).isEqualTo("Guillaume");
        assertThat(firstUser.lastname).isEqualTo("Thomas");
        assertThat(firstUser.login).isEqualTo("mthomas");
        assertThat(firstUser.password).isEqualTo("9876543210");
        assertThat(firstUser.expireDate).isEqualTo(LocalDate.of(2011, 11, 5));
    }

    @Test
    public void should_find_file_path_by_filename() {
        Path fileWithName = UserParser.findRecursivelyFileByName(".", "UserParser.java");

        assertThat(fileWithName.toString()).isEqualTo("./src/main/java/fr/xebia/xke/step3/UserParser.java");

    }

    @Test
    public void should_not_find_file_path_by_filename_when_file_not_exist() {
        Path fileWithName = UserParser.findRecursivelyFileByName(".", "UserParser123.java");

        assertThat(fileWithName).isNull();

    }
}
