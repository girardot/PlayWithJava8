package fr.xebia.xke.step4;

import fr.xebia.xke.java8.step4.TextAnalyser;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.api.Assertions.assertThat;

public class TextAnalyserTest {

    fr.xebia.xke.java7.step4.TextAnalyser textAnalyserJava7 = new fr.xebia.xke.java7.step4.TextAnalyser("src/main/resources/le corbeau et le renard.txt");
    TextAnalyser textAnalyserJava8 = new TextAnalyser("src/main/resources/le corbeau et le renard.txt");

    @Test
    public void should_return_words() throws IOException {

        assertThat(textAnalyserJava7.words()).isEqualTo(textAnalyserJava8.words());
    }

    @Test
    public void should_return_words_occurrence() throws IOException {
        TextAnalyser textAnalyser = new TextAnalyser("src/main/resources/le corbeau et le renard.txt");
        assertThat(textAnalyserJava7.wordOccurrence()).isEqualTo(textAnalyser.wordOccurrence());
    }
}
