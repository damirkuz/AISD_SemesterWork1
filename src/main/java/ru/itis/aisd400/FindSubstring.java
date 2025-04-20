package ru.itis.aisd400;

import java.util.ArrayList;
import java.util.List;

public class FindSubstring {
    private int iterationCount;

    public FindSubstring() {
        this.iterationCount = 0;
    }

    public int getIterationCount() {
        return iterationCount;
    }


    public List<Integer> KMP(String pattern, String text, boolean findAll) {
        // pattern - подстрока, text - строка
        // Проверка на корректность входных данных, если pattern или text пустые — выходим
        if (pattern == null || pattern.isEmpty() || text == null || text.isEmpty()) {
            return null;
        }

        // Список для хранения позиций всех найденных вхождений
        // Сохраняем начало и конец каждого вхождения последовательно
        List<Integer> positions = new ArrayList<>();

        // Вычисляем префикс-функцию для подстроки pattern
        int[] prefix = fillPrefix(pattern);

        // i — индекс в строке text, j — индекс в подстроке pattern
        int i = 0, j = 0;

        // Проходим по всей строке text
        while (i < text.length()) {
            iterationCount++;

            // Если текущие символы совпадают, то продолжаем сравнение
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
                i++;

                // Если вся подстрока pattern найдена в text
                if (j == pattern.length()) {
                    // Добавляем индексы начала и конца найденного вхождения
                    positions.add(i - j);     // начало вхождения
                    positions.add(i - 1);     // конец вхождения

                    if (!findAll) {
                        return positions;
                    } else {
                        // Продолжаем поиск, используя префикс-функцию
                        j = prefix[j - 1];
                    }
                }

            } else {
                // Если несовпадение после нескольких совпавших символов —
                // используем префикс-функцию для "отката" j
                if (j != 0) {
                    j = prefix[j - 1];
                } else {
                    // Если ни один символ не совпал — просто сдвигаем i
                    i++;
                }
            }
        }

        // Если были найдены вхождения — возвращаем список позиций
        return positions.isEmpty() ? null : positions;
    }



    public int[] fillPrefix(String pattern) {
        // Массив префикс-функции, по индексу i хранится длина наибольшего префикс-суффикса
        int[] prefix = new int[pattern.length()];

        // j — указатель на текущую длину совпадающего префикс-суффикса
        // i — текущий индекс, по которому идём по строке pattern
        int j = 0, i = 1;

        // Проходим по всей строке pattern, начиная с индекса 1
        while (i < pattern.length()) {
            iterationCount++;

            // Если текущие символы pattern[i] и pattern[j] совпадают
            if (pattern.charAt(i) == pattern.charAt(j)) {
                // Увеличиваем длину текущего совпадения и сохраняем в prefix[i]
                prefix[i++] = j + 1;
                j++;
            } else {
                // Если не совпадают, и j > 0 — "откатываемся" по префикс-функции
                if (j != 0) {
                    j = prefix[j - 1];
                } else {
                    // Иначе (если j == 0), совпадений нет — префикс длины 0
                    prefix[i++] = 0;
                }
            }
        }

        return prefix;
    }


}
