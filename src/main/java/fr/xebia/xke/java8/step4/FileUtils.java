package fr.xebia.xke.java8.step4;

import fr.xebia.xke.java8.data.Address;
import fr.xebia.xke.java8.data.Role;
import fr.xebia.xke.java8.data.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileUtils {

    public static List<User> loadUsersFromCsv(String csvPath) {
        //TODO: Replace By Files.line
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

    public static Path findRecursivelyFileByName(String path, String fileName) throws IOException {
        //TODO:replace by Files.walk
        Path rootDictory = Paths.get(path);

        SearchVisitor searchVisitor = new SearchVisitor(fileName);

        Files.walkFileTree(rootDictory, searchVisitor);
        Path fileFound = searchVisitor.fileFound;
        if (fileFound == null) {
            throw new FileNotFoundException();
        }
        return fileFound;
    }


    private static User lineToUser(String line) {
        String[] columns = line.split(",");
        User user = new User(columns[0], columns[1], columns[2]);
        user.withLogin(columns[3])
                .withPassword(columns[4])
                .withExpireDate(LocalDate.parse(columns[5], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.")))
                .withRole(Role.valueOf(columns[6]))
        ;
        if (columns.length > 8) {
            user.withAddress(new Address(columns[7], columns[8], columns[9]));
        }

        return user;
    }

    private static Path getFileFromPath(String csvPath) throws URISyntaxException {
        return Paths.get(FileUtils.class.getClassLoader().getResource(csvPath).toURI());
    }

    public static class SearchVisitor extends SimpleFileVisitor<Path> {

        private Path fileFound;
        private String fileNameToSearch;

        public SearchVisitor(String fileNameToSearch) {
            this.fileNameToSearch = fileNameToSearch;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (attrs.isRegularFile() && file.getFileName().toString().equals(fileNameToSearch)) {
                fileFound = file;
                return FileVisitResult.TERMINATE;
            }
            return FileVisitResult.CONTINUE;
        }
    }

}
