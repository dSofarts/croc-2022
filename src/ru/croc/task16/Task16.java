package ru.croc.task16;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Task16 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к папке с логами: ");
        String pathText = scanner.nextLine();
        Path path = Path.of(pathText); // "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task16/logs"

        // Создаем коллекцию с объединенными логами и сортируем ее
        List<Log> logs = LogFilesReader.readLogsInFolder(path);
        Collections.sort(logs, new LogComparator());

        // Выводим логи
        for (Log log : logs) {
            System.out.println(log.getLog());
        }
    }

}
