package ru.croc.task8;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Task8 {

    public static void main(String[] args) throws IOException {

        String path;

        // Проверка на пустое значение args[0], согда задаем путь по умолчанию
        try {
            path = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            path = "/croc-2022/src/ru/croc/task8/text.txt";
        }

        System.out.println(countNumberOfWordsInFile(path));

    }

    /**
     * Подсчет колличество слов в файле
     * @param path путь к файлу
     * @return колличество слов в файле
     */
    public static int countNumberOfWordsInFile(String path) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader r = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {

            // Читаем символы в файле
            int c;
            while ((c = r.read()) != -1) {
                stringBuilder.append((char) c);
            }

            // Разделяем строку на слова
            String[] str = stringBuilder.toString().trim().split("\\s+");
            if (str.length == 1 && str[0].equals("")) {
                return 0;
            }
            return str.length;
        }
    }
}
