package fr.xebia.xke.step3;

import org.junit.Test;

import java.nio.file.Path;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserParserTest {

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
