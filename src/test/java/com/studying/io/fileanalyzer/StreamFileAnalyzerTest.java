package com.studying.io.fileanalyzer;

public class StreamFileAnalyzerTest extends FileAnalyzerTest {
    @Override
    protected FileAnalyzer getFileAnalyzer() {
        return new StreamFileAnalyzer();
    }

}
