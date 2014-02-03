package fr.xebia.xke.java7.tools;

import fr.xebia.xke.java7.domain.User;
import fr.xebia.xke.java7.step2.DateUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserParser {

    public static List<User> fromCsv(String csvPath) {
        try {
            Stream<String> lines = Files.lines(getFileFromPath(csvPath));
            return lines.skip(1).map(UserParser::lineToUser).collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static Path getFileFromPath(String csvPath) throws URISyntaxException {
        return Paths.get(UserParser.class.getClassLoader().getResource(csvPath).toURI());
    }

    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(columns[0], columns[1]);
        user.withLogin(columns[2])
                .withPassword(columns[3])
                .withbirthday(DateUtils.parseDateTime(columns[4]))
                .withStartDate(DateUtils.parseDateTime(columns[5]))
                .withEndDate(DateUtils.parseDateTime(columns[6]));

        return user;
    }
}
