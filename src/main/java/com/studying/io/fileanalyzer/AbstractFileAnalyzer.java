package com.studying.io.fileanalyzer;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractFileAnalyzer implements FileAnalyzer {
    protected Matcher matcher;
    protected int counter = 0;

    @Override
    public FileStatistics analyze(String pathToFile, String word) throws IOException {
        String content = readContent(pathToFile);
        List<String> sentences = splitIntoSentences(content);
        List<String> filteredSentences = filter(sentences, word);
        int count = countWord(filteredSentences, word);

        return new FileStatistics(filteredSentences, count);
    }

    @Override
    public abstract String readContent(String path) throws IOException;

    @Override
    public abstract List<String> splitIntoSentences(String content);

    @Override
    public abstract List<String> filter(List<String> sentences, String word);

    @Override
    public abstract int countWord(List<String> filteredSentences, String word);

    protected static Pattern getPattern(String word) {
        String regex = "\\b%s\\b".formatted(word);
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }
}
