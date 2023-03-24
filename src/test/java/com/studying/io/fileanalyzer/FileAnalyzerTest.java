package com.studying.io.fileanalyzer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {

    @DisplayName("test splitIntoSentences with separators (.!&)")
    @Test
    void testSplitIntoSentences() {
        String content = "Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> listExpected = List.of("Duck!", "One more duck.", "How many ducks here?", "Two ducks.", "Duck is beautiful.");
        List<String> resultList = FileAnalyzer.splitIntoSentences(content);
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test filter sentences with exact word")
    @Test
    void testFilter() {
        String content = "Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> listExpected = List.of("Duck!", "One more duck.", "Duck is beautiful.");
        List<String> splitSentences = FileAnalyzer.splitIntoSentences(content);
        List<String> resultList = FileAnalyzer.filter(splitSentences, "duck");
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test countWord")
    @Test
    void testCountWord() {
        String content = "Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> splitSentences = FileAnalyzer.splitIntoSentences(content);
        List<String> filteredSentences = FileAnalyzer.filter(splitSentences, "duck");
        assertEquals(3, FileAnalyzer.countWord(filteredSentences, "duck"));
    }

    @DisplayName("test count word in file")
    @Test
    void testCountWordInFile() throws IOException {
        FileAnalyzerRunner.writeDuckBook(FileAnalyzerRunner.FILE_NAME);
        String content = FileAnalyzer.readContent(FileAnalyzerRunner.FILE_NAME);
        List<String> splitSentences = FileAnalyzer.splitIntoSentences(content);
        List<String> filteredSentences = FileAnalyzer.filter(splitSentences, "duck");
        assertEquals(25, FileAnalyzer.countWord(filteredSentences, "duck"));
    }

}
