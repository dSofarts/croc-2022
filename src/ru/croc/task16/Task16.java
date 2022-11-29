package ru.croc.task16;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class Task16 {

    public static void main(String[] args) throws IOException {

        Path path = Path.of("/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task16/logs");
        LogFilesReader.readFilesInFolder(path);
        List<Log> logs = LogFilesReader.getLogs();
        Collections.sort(logs, new LogComparator());
        for (Log log : logs) {
            System.out.println(log.getLog());
        }
    }

}
