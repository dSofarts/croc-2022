package ru.croc.task16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogFilesReader {

    private static List<Log> logs = new ArrayList<>();

    /**
     * Прочитать логи в папке
     * @param path путь к папке
     * @return List с логами
     */
    public static List<Log> readLogsInFolder(Path path) throws IOException {
        List<File> filesInFolder = Files.walk(path).filter(Files::isRegularFile)
                .filter(file -> file.getFileName().toString().endsWith(".log") || file.getFileName()
                        .toString().endsWith(".trace")).map(Path::toFile).toList();

        for (File file : filesInFolder) {
            readFile(file);
        }

        return logs;
    }

    /**
     * Прочитать логи в файле
     * @param file файл
     */
    private static void readFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String logLine;
            while ((logLine = reader.readLine()) != null) {
                logs.add(new Log(Long.parseLong(logLine.split(" ")[0]), logLine));
            }
        }
    }

}