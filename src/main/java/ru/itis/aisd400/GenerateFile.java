package ru.itis.aisd400;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

public class GenerateFile {
    private static final int COUNTSTRINGS = 1000;
    private static final Path FILE_PATH = Path.of("src/main/resources/testStrings.txt");
    private static final int PERCENT_OF_OCCURENCE = 100;
    private static final Random RANDOM = new Random();


    public static void main(String[] args) {
        generateFile();
    }



    private static String generateString(int lengthString, int lengthSubstring) {
        Random r = RANDOM;
        StringBuilder sbString = new StringBuilder();
        StringBuilder sbSubstring = new StringBuilder();

        for (int i = 0; i < lengthSubstring; i++) {
            sbSubstring.append((char) ('a' + r.nextInt(26)));
        }

        String substring = sbSubstring.toString();

        if (r.nextInt(100) < PERCENT_OF_OCCURENCE) {

            for (int i = 0; i < lengthString - lengthSubstring; i++) {
                sbString.append((char) ('a' + r.nextInt(26)));
            }

            int insertPos = r.nextInt(sbString.length() + 1);
            sbString.insert(insertPos, substring);

        } else {
            for (int i = 0; i < lengthString; i++) {
                sbString.append((char) ('a' + r.nextInt(26)));
            }
        }

        return substring + ";" + sbString + "\n";
    }


    private static void generateFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH.toFile()))) {
            Random r = RANDOM;

            for (int i = 0; i < COUNTSTRINGS; i++) {
                int lengthString = r.nextInt(100, 10_000);
                int lengthSubstring = r.nextInt(10, lengthString / 2);

                writer.write(generateString(lengthString, lengthSubstring));
            }

            System.out.println("Файл успешно сгенерирован: " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Ошибка при записи файла: " + e.getMessage());
        }


    }
}
