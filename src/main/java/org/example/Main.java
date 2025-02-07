package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser(args);
        FileProcessor fileProcessor = new FileProcessor();

        try {
            Map<String, List<String>> dataMap = new HashMap<>();
            for (String file : parser.getInputFiles()) {
                List<String> lines = fileProcessor.readFile(file);
                fileProcessor.classifyLines(lines, dataMap);
            }
            fileProcessor.writeOutputFiles(dataMap, ".", "", false);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}