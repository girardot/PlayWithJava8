package fr.xebia.xke.java8.step4;

import fr.xebia.xke.java8.data.Address;
import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtils {

    public static List<User> loadUsersFromCsv(Path csvPath) {
        try {
            Stream<String> lines = Files.lines(csvPath, StandardCharsets.UTF_8);
            return lines.skip(1).map(FileUtils::lineToUser).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static Path findRecursivelyFileByName(String path, String fileName) throws IOException {
        Path rootDictory = Paths.get(path);
        Stream<Path> pathStream = Files.walk(rootDictory, 30);
        Optional<Path> pathOptional = pathStream.filter(p -> p.getFileName().toString().equals(fileName)).findFirst();
        return pathOptional.orElseThrow(FileNotFoundException::new);
    }

    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(columns[0], columns[1], columns[2]);
        user.withLogin(columns[3])
                .withPassword(columns[4])
                .withExpireDate(LocalDate.parse(columns[5], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")))
                .withRole(Role.valueOf(columns[6]));

        if (columns.length > 8) {
            user.withAddress(new Address(columns[7], columns[8], columns[9]));
        }

        return user;
    }

}
