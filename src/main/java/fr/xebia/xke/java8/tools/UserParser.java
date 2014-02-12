package fr.xebia.xke.java8.tools;

import fr.xebia.xke.domain.Address;
import fr.xebia.xke.java8.domain.User;
import fr.xebia.xke.java8.step1.DateUtils;

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
                .withbirthday(DateUtils.parseDateTime(columns[4]).toLocalDate())
                .withStartDate(DateUtils.parseDateTime(columns[5]).toLocalDate())
                .withEndDate(DateUtils.parseDateTime(columns[6]).toLocalDate())
                .withBillingAddress(new Address(columns[7], columns[8], columns[9]))
        ;

        if (columns.length >= 13) {
            user.withDeliveryAddress(new Address(columns[10], columns[11], columns[12]));
        }

        return user;
    }

}
