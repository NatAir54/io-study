package com.studying.io.fileanalyzer;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamFileAnalyzer extends AbstractFileAnalyzer {
    @Override
    public String readContent(String path) throws IOException {
        File file = new File(path);
        StringBuilder result = new StringBuilder();
        try (Stream<String> linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(result::append);
        }
        return result.toString();
    }

    @Override
    public List<String> splitIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> list = Stream.of(sentences)
                .map(String::trim)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<String> filter(List<String> sentences, String word) {
        Pattern pattern = getPattern(word);
        List<String> filteredList = sentences.stream()
                .filter(s -> {
                    matcher = pattern.matcher(s);
                    return matcher.find();
                }).collect(Collectors.toList());
        return filteredList;
    }

    @Override
    public int countWord(List<String> filteredSentences, String word) {
        Optional<Integer> result = filteredSentences.stream()
                .map(s -> countWordInSentence(s, word))
                .reduce(Integer::sum);
        return result.orElse(0);
    }

    private static int countWordInSentence(String sentence, String word) {
        Pattern pattern = getPattern(word);
        Matcher matcher = pattern.matcher(sentence);
        int counter = 0;
        while (matcher.find()) {
              counter++;
        }
        return counter;
    }
}
