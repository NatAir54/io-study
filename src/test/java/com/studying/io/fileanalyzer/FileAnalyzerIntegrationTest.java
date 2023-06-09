package com.studying.io.fileanalyzer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileAnalyzerIntegrationTest {
    private static final String FILE_NAME = "src/test/resources/DuckBook.txt";
    private FileAnalyzer fileAnalyzer = new StreamFileAnalyzer();

    @DisplayName("test split file text into sentences with separators (.!?) work fine")
    @Test
    void testSplitFileTextIntoSentencesWorkFine() throws IOException {
        String content = fileAnalyzer.readContent(FILE_NAME);
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        assertEquals(43, splitSentences.size());
    }

    @DisplayName("test filter sentences with word in file work fine")
    @Test
    void testFilterSentencesWithWordInFileWorkFine() throws IOException {
        String content = fileAnalyzer.readContent(FILE_NAME);
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> filteredSentences = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(23, filteredSentences.size());
    }

    @DisplayName("test count word in file work fine")
    @Test
    void testCountWordInFileWorkFine() throws IOException {
        String content = fileAnalyzer.readContent(FILE_NAME);
        List<String> splitSentences = fileAnalyzer.splitIntoSentences(content);
        List<String> filteredSentences = fileAnalyzer.filter(splitSentences, "duck");
        assertEquals(25, fileAnalyzer.countWord(filteredSentences, "duck"));
    }

    @Test
    void testAnalyzeWorkFine() throws IOException {
        writeDuckBook(FILE_NAME);
        FileStatistics fileStatistics = fileAnalyzer.analyze(FILE_NAME,"duck");

        printSentencesWithWord(fileStatistics.getSentences());
        printWordCount(fileStatistics.getWordCount());
    }

    private static void printSentencesWithWord(List<String> filteredSentences) {
        filteredSentences.forEach(System.out :: println);
    }

    private static void printWordCount(int count) {
        System.out.println(count);
    }



    private static void writeDuckBook(String path) throws IOException {
        String text = "What Is a Domestic Duck? " +
                "Domestic duck is a duck that is cared for and/or raised by people. " +
                "People raise ducks for many reasons, but the common ones are pets, eggs, meat, and show. " +
                "Because duck is so reliant on humans, it don't last long in the wild on its own it relies on people for food and shelter. " +
                "By classification, domestic duck, with the exception of the Muscovy, is any descendant of the mallard duck. " +
                "Southeast Asia was the first known area to start farming and domesticating mallards over 4000 years ago! " +
                "This practice has continued through Egyptian, Roman, and current times. " +
                "This duck became polygamous after domestication. " +
                "Mallards typically mate with one partner at a time, but maybe being in a confined space changed that mentality? " +
                "Domestication provided the ability to have more influence over reproduction and served to secure a predictable resource from the ducks. " +
                "Domestication also made ducks less aggressive and easier to handle than wild ducks. " +
                "Domestic duck makes great pets and companions for life. " +
                "No, they don't need a pond, just enough water to dip their heads into and clean out their beaks. " +
                "They are resilient to extreme weather and easy to care for. " +
                "Duck only needs enough water for cleaning out their nostrils by dunking their heads, so a bowl will do. " +
                "Ducks are friendly, curious little creatures, and each one comes with its own unique personality. " +
                "Domestic duck is great in the garden and enjoyable to have in the yard. " +
                "They are also great around children because their bites don't typically hurt! " +
                "Duck does need to be protected from common predators such as hawks or the neighbor's pet dog, and any standard chicken tractor or pen to lock them up at night will do. " +
                "Some breeds of domestic duck are raised purely for show. " +
                "These show ducks display beautiful crests, striking plumage, and decorative tufts. " +
                "Buff Orpington, Silver Appleyard, Rouen, Saxony, and Faun Runners are amongst my favorite show birds. " +
                "Domestic duck has been a part of society and culture for many years. " +
                "Evidence suggests that Egyptians believed the duck to be a symbol of fertility. " +
                "Ducks, like chickens, have been farmed for thousands of years by people all over the world. " +
                "Duck can be penned up, kept in batteries, or allowed to free roam. " +
                "They are pretty resilient creatures that are capable of surviving harsh conditions. " +
                "The domestic duck is farmed for a variety of reasons, including down, meat, eggs, oil, pets foie gras, and even their blood! " +
                "In the Western Hemisphere, domestic duck farming is not as popular as chicken farming because chickens are easier to keep confined and produce more meat per bird. " +
                "The overall lower cost of care makes chickens more desirable. " +
                "With the exception of the Muscovy duck, most domestic ducks are horrible egg sitters! " +
                "Duck does not usually sit on their eggs, so its eggs must be incubated in order to hatch. " +
                "This factor, again, raises the costs of duck farming. " +
                "Overall, ducks are healthier for you to consume. " +
                "The eggs are typically 30 percents bigger than chicken eggs, and duck eggs provide more of the essential vitamins and minerals your body needs. " +
                "If you have ever raised any type of poultry, caring for a domestic duck is fairly straightforward. " +
                "As ducklings, they should be kept away from deep water. " +
                "Ducks are not typically with their mothers at an early age, and the babies do not have their preen oil yet. " +
                "Ducklings can easily drown if they get too wet. " +
                "Duck does not require water to swim, they just need enough water to dunk their heads and clean their bills. " +
                "Raising a domestic duck is similar to raising any other type of poultry. " +
                "Duck need to be brooded through their younger age and fed the same types of foods as chickens throughout the stages of their lives. " +
                "Ducks are more resilient, but they also have the same health problems as other poultry.";
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)))) {
            bw.write(text);
            bw.flush();
        }
    }
}
