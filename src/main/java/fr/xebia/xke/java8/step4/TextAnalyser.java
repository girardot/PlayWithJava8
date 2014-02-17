package fr.xebia.xke.java8.step4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalyser {

    public static final String REGEX_CHAR_ONLY = "^[a-z]+$";
    private Path filePath;

    public TextAnalyser(String path) {
        filePath = new File(path).toPath();
    }

    public Set<String> words() throws IOException {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.map(line -> line.toLowerCase()).
                    flatMap(line -> Stream.of(line.split(" ")).
                            filter(c -> c.matches(REGEX_CHAR_ONLY))).
                    collect(Collectors.toSet());
        }
    }

    public Map<String, Long> wordOccurrence() throws IOException {
        return Files.lines(filePath).map(String::toLowerCase).
                flatMap(line -> Stream.of(line.split(" ")).
                        filter(c -> c.matches(REGEX_CHAR_ONLY))).
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
