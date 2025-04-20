package ru.itis.aisd400;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Testing {
    private static final Path IN_FILE_PATH = Path.of("src/main/resources/testStrings.txt");
    private static final Path OUT_FILE_PATH = Path.of("src/main/resources/result.txt");

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(IN_FILE_PATH.toFile());
        BufferedWriter writer = new BufferedWriter(new FileWriter(OUT_FILE_PATH.toFile()));

        writer.write("duration;iterationCount;patternLength;textLength\n");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            long[] result = testKMPWithMetrics(parts[0], parts[1], false);
            String resultString = String.format("%d;%d;%d;%d\n", result[0], result[1], result[2], result[3]);
            writer.write(resultString);
        }
        writer.close();
    }

    private static void test1() {
        FindSubstring findSubstring = new FindSubstring();
        findSubstring.KMP("лииллиил", "123", true);
    }

    private static void test2() {
        FindSubstring findSubstring = new FindSubstring();
        String pattern = "лилила";
        String text = "лилилось лилилась";

        System.out.println(findSubstring.KMP(pattern, text, true));
    }

    private static long[] testKMPWithMetrics(String pattern, String text, boolean findAll) {
        long startTime = System.nanoTime(); // Время начала
        FindSubstring findSubstring = new FindSubstring();

        findSubstring.KMP(pattern, text, findAll);

        long endTime = System.nanoTime(); // Время конца
        long duration = endTime - startTime; // Время работы в наносекундах
        int iterationCount = findSubstring.getIterationCount();

        return new long[]{duration, iterationCount, pattern.length(), text.length()};
    }
}
