package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FileProcessor {
    public List<String> readFile(String filePath) throws Exception {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public void writeOutputFiles(Map<String, List<String>> dataMap, String outputDir, String prefix, boolean appendMode) throws IOException {
        for (Map.Entry<String, List<String>> entry : dataMap.entrySet()) {
            String fileName = prefix + entry.getKey() + ".txt";
            Path outputPath = Path.of(outputDir, fileName);
            Files.createDirectories(outputPath.getParent());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile(), appendMode))) {
               for (String line : entry.getValue()) {
                   writer.write(line);
                   writer.newLine();
               }
            }
        }
    }

    public void classifyLines(List<String> lines, Map<String, List<String >> dataMap) {
        for (String line : lines) {
            if (isInteger(line)) {
                dataMap.computeIfAbsent("integers", k -> new ArrayList<>()).add(line);
            } else if (isFloat(line)) {
                dataMap.computeIfAbsent("floats", k -> new ArrayList<>()).add(line);
            } else {
                dataMap.computeIfAbsent("strings", k -> new ArrayList<>()).add(line);
            }
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
