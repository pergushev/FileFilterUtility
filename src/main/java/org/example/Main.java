package org.example;

public class Main {
    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser(args);
        System.out.printf("Input files: " + parser.getInputFiles());

    }
}