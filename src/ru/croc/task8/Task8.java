package ru.croc.task8;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Task8 {

    public static void main(String[] args) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String path;

        // Проверка на пустое значение args[0], согда задаем путь по умолчанию
        try {
            path = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            path = "/croc-2022/src/ru/croc/task8/text.txt";
        }

        // Читаем файл
        try (Reader r = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8)) {

            // Читаем символы в файле
            int c;
            while ((c = r.read()) != -1) {
                stringBuilder.append((char) c);
            }

            // Разделяем строку на слова
            String[] str = stringBuilder.toString().trim().split(" +|\n|\n +");
            System.out.println(str.length);
        }
    }
}
