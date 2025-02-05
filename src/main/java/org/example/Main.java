package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser(args);
        FileProcessor fileProcessor = new FileProcessor();

        try {
            for (String file : parser.getInputFiles()) {
                System.out.println("Processing file: " + file);
                List<String> lines = fileProcessor.readFile(file);
                System.out.println("Lines: " + lines);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}