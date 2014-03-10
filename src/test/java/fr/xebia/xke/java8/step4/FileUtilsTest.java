package fr.xebia.xke.java8.step4;

import fr.xebia.xke.java8.data.User;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

public class FileUtilsTest {

    @Test
    public void should_parse_user_csv() {
        List<User> users = FileUtils.loadUsersFromCsv("users.csv");

        assertThat(users).hasSize(1000);
        User firstUser = users.get(0);
        assertThat(firstUser.getTitle()).isEqualTo("Mme");
        assertThat(firstUser.getFirstname()).isEqualTo("Colette");
        assertThat(firstUser.getLastname()).isEqualTo("Bernard");
        assertThat(firstUser.getLogin()).isEqualTo("colettebernard");
        assertThat(firstUser.getPassword()).isEqualTo("9876543210");
        assertThat(firstUser.getExpireDate()).isEqualTo(LocalDate.of(2000, 12, 9));
    }

    @Test
    public void should_find_file_path_by_filename() throws IOException {
        Path fileWithName = FileUtils.findRecursivelyFileByName(".", "FileUtils.java");

        assertThat(fileWithName.toString()).isEqualTo("./src/main/java/fr/xebia/xke/java8/step4/FileUtils.java");

    }

    @Test(expected = FileNotFoundException.class)
    public void should_not_find_file_path_by_filename_when_file_not_exist() throws IOException {
        Path fileWithName = FileUtils.findRecursivelyFileByName(".", "UserParser123.java");

        assertThat(fileWithName).isNull();

    }
}
