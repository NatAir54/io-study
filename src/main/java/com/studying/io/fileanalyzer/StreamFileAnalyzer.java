package com.studying.io.fileanalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StreamFileAnalyzer extends AbstractFileAnalyzer {

    @Override
    public List<String> splitIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> list = new ArrayList<>();
        Stream<String> sentencesStream = Stream.of(sentences);
        sentencesStream.forEach(s -> list.add(s.trim()));
        return list;
    }

    @Override
    public List<String> filter(List<String> sentences, String word) {
        String regex = "\\b%s\\b".formatted(word);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        List<String> filteredList = new ArrayList<>();
        sentences.stream()
                .forEach(s -> {
                    matcher = pattern.matcher(s);
                    if (matcher.find()) {
                        filteredList.add(s);
                    }
                });
        return filteredList;
    }

    @Override
    public int countWord(List<String> filteredSentences, String word) {
        String regex = "\\b%s\\b".formatted(word);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        filteredSentences.stream()
                .forEach(s -> {
                    matcher = pattern.matcher(s);
                    while (matcher.find()) {
                        counter++;
                    }
                });
        return counter;
    }
}
