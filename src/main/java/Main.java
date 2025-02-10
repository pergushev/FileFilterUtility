import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            CommandLineParser parser = new CommandLineParser(args);
            List<String> inputFiles = parser.getInputFiles();
            String outputDir = parser.getOutputDirectory();
            String prefix = parser.getPrefix();
            boolean appendMode = parser.isAppendMode();
            boolean shortStats = parser.isShortStats();
            boolean fullStats = parser.isFullStats();

            FileProcessor processor = new FileProcessor(outputDir, prefix, appendMode);

            // Обработка входных файлов
            processor.processFiles(inputFiles);

            // Получение объекта Statistics
            Statistics stats = processor.getStatistics();

            // Вывод статистики, если запрошено
            if (shortStats || fullStats) {
                System.out.println("Statistics:");
                if (shortStats) {
                    stats.printShortStats();
                }
                if (fullStats) {
                    stats.printFullStats();
                }
            }
        } catch (IllegalArgumentException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}