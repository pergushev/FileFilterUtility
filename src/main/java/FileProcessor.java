import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileProcessor {
    private final String outputDir;
    private final String prefix;
    private final boolean appendMode;
    private final Map<String, List<String>> dataMap = new HashMap<>();
    private final Statistics statistics = new Statistics();

    public FileProcessor(String outputDir, String prefix, boolean appendMode) {
        this.outputDir = outputDir;
        this.prefix = prefix;
        this.appendMode = appendMode;
    }

    public void processFiles(List<String> inputFiles) throws IOException {
        for (String file : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    classifyLine(line.trim());
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + file);
            }
        }

        writeOutputFiles();
    }

    private void classifyLine(String line) {
        if (isInteger(line)) {
            dataMap.computeIfAbsent("integers", k -> new ArrayList<>()).add(line);
            statistics.addInteger(Long.parseLong(line)); // Собираем статистику для целых чисел
        } else if (isFloat(line)) {
            dataMap.computeIfAbsent("floats", k -> new ArrayList<>()).add(line);
            statistics.addFloat(Double.parseDouble(line)); // Собираем статистику для вещественных чисел
        } else {
            dataMap.computeIfAbsent("strings", k -> new ArrayList<>()).add(line);
            statistics.addString(line); // Собираем статистику для строк
        }
    }

    private boolean isInteger(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void writeOutputFiles() throws IOException {
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

    public Statistics getStatistics() {
        return statistics;
    }
}
