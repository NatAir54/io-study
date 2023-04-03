package com.studying.io.fileanalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BasicFileAnalyzer extends AbstractFileAnalyzer {
    @Override
    public String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        byte[] contentArray;
        try (InputStream inputStream = new FileInputStream(pathToFile)) {
            int fileLength = (int) pathToFile.length();
            contentArray = new byte[fileLength];
            inputStream.read(contentArray);
        }
        return new String(contentArray);
    }

    public List<String> splitIntoSentences(String content) {
        String[] sentences = SENTENCE_PATTERN.split(content);
        List<String> list = new ArrayList<>();
        for (String sentence : sentences) {
            list.add(sentence.trim());
        }
        return list;
    }


    public List<String> filter(List<String> sentences, String word) {
        String regex = "\\b%s\\b".formatted(word);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        List<String> filteredList = new ArrayList<>();
        for (String sentence : sentences) {
            matcher = pattern.matcher(sentence);
            if (matcher.find()) {
                filteredList.add(sentence);
            }
        }
        return filteredList;
    }


    public int countWord(List<String> filteredSentences, String word) {
        String regex = "\\b%s\\b".formatted(word);
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        for (String sentence : filteredSentences) {
            matcher = pattern.matcher(sentence);
            while (matcher.find()) {
                counter++;
            }
        }
        return counter;
    }
}