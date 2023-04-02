package com.studying.io.fileanalyzer;

public class BasicFileAnalyzerTest extends FileAnalyzerTest{
    @Override
    protected FileAnalyzer getFileAnalyzer() {
        return new BasicFileAnalyzer();
    }

}