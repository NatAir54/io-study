package com.studying.io.fileanalyzer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerTest {

    @Test
    void testSplitIntoSentences() {
        String content = "Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> listExpected = List.of("Duck!", "One more duck.", "How many ducks here?", "Two ducks.", "Duck is beautiful.");
        List<String> resultList = FileAnalyzer.splitIntoSentences(content);
        assertEquals(listExpected, resultList);
    }
}
