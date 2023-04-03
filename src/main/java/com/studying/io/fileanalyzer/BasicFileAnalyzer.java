package com.studying.io.fileanalyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BasicFileAnalyzer extends AbstractFileAnalyzer {
    @Override
    public String readContent(String path) throws IOException {
        File pathToFile = new File(path);
        StringBuilder result = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
            while (br.ready()) {
                result.append(br.readLine());
            }
        }
        return result.toString();
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
        Pattern pattern = getPattern(word);
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
        Pattern pattern = getPattern(word);
        int counter = 0;
        for (String sentence : filteredSentences) {
            matcher = pattern.matcher(sentence);
            while (matcher.find()) {
                counter++;
            }
        }
        return counter;
    }
}