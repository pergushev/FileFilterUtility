package org.example;

import java.util.List;

public class CommandLineParser {
    private final String[] args;

    public CommandLineParser(String[] args) {
        this.args = args;
    }

    public List<String> getInputFiles() {
        return List.of("in1.txt", "in2.txt");
    }
}
