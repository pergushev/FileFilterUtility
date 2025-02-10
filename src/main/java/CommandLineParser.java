import java.util.List;
import java.util.ArrayList;


public class CommandLineParser {
    private final String[] args;
    private String outputDir = "."; // По умолчанию текущая директория
    private String prefix = "";     // По умолчанию без префикса
    private boolean appendMode = false; // По умолчанию режим перезаписи
    private boolean shortStats = false;
    private boolean fullStats = false;
    private List<String> inputFiles = new ArrayList<>();

    public CommandLineParser(String[] args) {
        this.args = args;
        parseArgs();
    }

    private void parseArgs() {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-o": // Опция для пути вывода
                    if (i + 1 < args.length) {
                        outputDir = args[++i];
                    } else {
                        throw new IllegalArgumentException("Missing value for -o option");
                    }
                    break;
                case "-p": // Опция для префикса
                    if (i + 1 < args.length) {
                        prefix = args[++i];
                    } else {
                        throw new IllegalArgumentException("Missing value for -p option");
                    }
                    break;
                case "-a": // Режим добавления
                    appendMode = true;
                    break;
                case "-s": // Краткая статистика
                    shortStats = true;
                    break;
                case "-f": // Полная статистика
                    fullStats = true;
                    break;
                default:
                    // Все остальные аргументы считаются входными файлами
                    inputFiles.add(args[i]);
            }
        }

        if (inputFiles.isEmpty()) {
            throw new IllegalArgumentException("No input files provided");
        }
    }

    public String getOutputDirectory() {
        return outputDir;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppendMode() {
        return appendMode;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public boolean isFullStats() {
        return fullStats;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
