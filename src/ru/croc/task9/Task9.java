package ru.croc.task9;

import java.util.Scanner;
import ru.croc.task9.exeptions.IncorrectPathException;

public class Task9 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        try {
            System.out.println(normalizePath(path));
        } catch (IncorrectPathException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Привести заданный путь к нормальному значению
     */
    public static String normalizePath(String path) throws IncorrectPathException {

        // Проверяем на корректность заданого пути
        if (path.startsWith("/") || path.endsWith("/") || !path.contains("/") || path.endsWith("..")) {
            throw new IncorrectPathException(path);
        }

        // Преобразуем в массив
        String[] wrongPath = path.split("/");
        // Если массив всего из двух элементов то выводим его не преобразуя
        if (wrongPath.length <= 2) {
            return String.join("/", wrongPath);
        }

        String[] rightPath;
        // Проверяем известен ли родитель последней директории
        if (!wrongPath[wrongPath.length - 2].contains(".")) {
            rightPath = new String[3];
            System.arraycopy(wrongPath, wrongPath.length - 2, rightPath, 1, 2);
        } else {
            rightPath = new String[2];
            System.arraycopy(wrongPath, wrongPath.length - 1, rightPath, 1, 1);
        }
        rightPath[0] = "..";

        // преобразуем массив в строку
        return String.join("/", rightPath);
    }
}
