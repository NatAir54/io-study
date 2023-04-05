package com.studying.io.fileanalyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class FileAnalyzerTest {
    private FileAnalyzer fileAnalyzer;

    protected abstract FileAnalyzer getFileAnalyzer();
    @BeforeEach
    public void before() {
        fileAnalyzer = getFileAnalyzer();
    }

    @DisplayName("test split String into sentences with separators (.!?) work fine")
    @Test
    void testSplitStringIntoSentencesWorkFine() {
        String content = "Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> listExpected = List.of("Duck!", "One more duck.", "How many ducks here?", "Two ducks.", "Duck is beautiful.");
        List<String> resultList = fileAnalyzer.splitIntoSentences(content);
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test split String into sentences without separators (.!?)")
    @Test
    void testSplitStringIntoSentencesWithoutIndicatedSeparators() {
        String content = "One more duck :) So many ducks here; World is beautiful";
        List<String> listExpected = List.of("One more duck :) So many ducks here; World is beautiful");
        List<String> resultList = fileAnalyzer.splitIntoSentences(content);
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test filter sentences with word work fine")
    @Test
    void testFilterSentencesWorkFine() {
        String content = "Hello! Wow, duck! One more duck. How many birds are here? Two birds. This duck is beautiful.";
        List<String> listExpected = List.of("Wow, duck!", "One more duck.", "This duck is beautiful.");
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> resultList = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test filter sentences with word when sentence is empty")
    @Test
    void testFilterSentencesWhenSentenceIsEmpty() {
        String content = "";
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> listExpected = Collections.emptyList();
        List<String> resultList = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test filter sentences with word in different case")
    @Test
    void testFilterSentencesWithWordInDifferentCase() {
        String content = "Hello! Duck! One more duck. How many birds are here? Two birds. Duck is beautiful.";
        List<String> listExpected = List.of("Duck!", "One more duck.", "Duck is beautiful.");
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> resultList = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test filter sentences with word in different case including word in plural")
    @Test
    void testFilterSentencesWithWordInDifferentCaseIncludingWordInPlural() {
        String content = "Hello! Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> listExpected = List.of("Duck!", "One more duck.", "Duck is beautiful.");
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> resultList = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test filter sentences when word is missing")
    @Test
    void testFilterSentencesWhenWordIsMissing() {
        String content = "Hello! How are you? No birds here?";
        List<String> listExpected = Collections.emptyList();
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> resultList = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(listExpected, resultList);
    }

    @DisplayName("test countWord when one or no searched word in sentence")
    @Test
    void testCountWordWhenOneOrNoSearchedWordInSentence() {
        String content = "Duck! One more duck. How many ducks here? Two ducks. Duck is beautiful.";
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> filteredSentences = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(3, fileAnalyzer.countWord(filteredSentences, "duck"));
    }

    @DisplayName("test countWord when more than one searched word in sentence")
    @Test
    void testCountWordWhenMoreThanOneSearchedWordInSentence() {
        String content = "Oh, duck, duck! One more duck. How many ducks here? Two ducks. This duck and that duck are beautiful.";
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> filteredSentences = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(5, fileAnalyzer.countWord(filteredSentences, "duck"));
    }
}
