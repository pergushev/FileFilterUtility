public class Statistics {
    private long integerCount = 0;
    private long floatCount = 0;
    private long stringCount = 0;

    private long minInt = Long.MAX_VALUE;
    private long maxInt = Long.MIN_VALUE;
    private long sumInt = 0;

    private double minFloat = Double.MAX_VALUE;
    private double maxFloat = Double.MIN_VALUE;
    private double sumFloat = 0;

    private int minLengthString = Integer.MAX_VALUE;
    private int maxLengthString = Integer.MIN_VALUE;

    public void addInteger(long num) {
        integerCount++;
        sumInt += num;
        minInt = Math.min(minInt, num);
        maxInt = Math.max(maxInt, num);
    }

    public void addFloat(double num) {
        floatCount++;
        sumFloat += num;
        minFloat = Math.min(minFloat, num);
        maxFloat = Math.max(maxFloat, num);
    }

    public void addString(String str) {
        stringCount++;
        int length = str.length();
        minLengthString = Math.min(minLengthString, length);
        maxLengthString = Math.max(maxLengthString, length);
    }

    public void printShortStats() {
        System.out.println("Integers: " + integerCount);
        System.out.println("Floats: " + floatCount);
        System.out.println("Strings: " + stringCount);
    }

    public void printFullStats() {
        if (integerCount > 0) {
            System.out.println("Integer Stats:");
            System.out.println("Min: " + minInt);
            System.out.println("Max: " + maxInt);
            System.out.println("Sum: " + sumInt);
            System.out.println("Average: " + (sumInt / (double) integerCount));
        }

        if (floatCount > 0) {
            System.out.println("Float Stats:");
            System.out.println("Min: " + minFloat);
            System.out.println("Max: " + maxFloat);
            System.out.println("Sum: " + sumFloat);
            System.out.println("Average: " + (sumFloat / floatCount));
        }

        if (stringCount > 0) {
            System.out.println("String Stats:");
            System.out.println("Min Length: " + minLengthString);
            System.out.println("Max Length: " + maxLengthString);
        }
    }
}
