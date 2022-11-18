package ru.croc.task9;

import java.nio.file.Path;
import java.util.Scanner;
import ru.croc.task9.exeptions.IncorrectPathException;

public class Task9 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Path path = Path.of(scanner.nextLine());

        try {
            System.out.println(normalizePath(path));
        } catch (IncorrectPathException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Привести заданный путь к нормальному значению
     */
    public static String normalizePath(Path path) throws IncorrectPathException {

        // Проверяем на корректность заданого пути
        if (path.startsWith("/") || path.endsWith("/")) {
            throw new IncorrectPathException(path);
        }

        return path.normalize().toString();
    }
}
