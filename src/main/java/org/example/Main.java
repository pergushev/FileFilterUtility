package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser(args);
        FileProcessor fileProcessor = new FileProcessor();

        try {
            for (String file : parser.getInputFiles()) {
                List<String> lines = fileProcessor.readFile(file);
                fileProcessor.classifyLines(lines);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}