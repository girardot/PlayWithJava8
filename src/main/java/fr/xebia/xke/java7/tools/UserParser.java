package fr.xebia.xke.java7.tools;

import fr.xebia.xke.domain.Address;
import fr.xebia.xke.java7.domain.User;
import fr.xebia.xke.java7.step1.DateUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserParser {

    public static List<User> fromCsv(String csvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(getFileFromPath(csvPath).toFile()))) {
            String line;
            boolean firstLine = true;
            List<User> users = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (!firstLine) {
                    users.add(lineToUser(line));
                }
                firstLine = false;
            }

            return users;

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(columns[0], columns[1]);
        user.withLogin(columns[2])
                .withPassword(columns[3])
                .withbirthday(DateUtils.parseDateTime(columns[4]))
                .withStartDate(DateUtils.parseDateTime(columns[5]))
                .withEndDate(DateUtils.parseDateTime(columns[6]))
                .withBillingAddress(new Address(columns[7], columns[8], columns[9]))
        ;

        if (columns.length >= 13) {
            user.withDeliveryAddress(new Address(columns[10], columns[11], columns[12]));
        }

        return user;
    }

    private static Path getFileFromPath(String csvPath) throws URISyntaxException {
        return Paths.get(UserParser.class.getClassLoader().getResource(csvPath).toURI());
    }
}
