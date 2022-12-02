package ru.croc.task13;

import java.io.IOException;
import java.util.Scanner;

public class Task13 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите вашу историю просмотров: ");
        String userMovie = scanner.nextLine();

        String pathToMovies = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task13/files/movies.txt";
        String pathToHistory = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task13/files/movieWatchHistory.txt";

        OnlineCinema onlineCinema = OnlineCinema.createFromFiles(pathToMovies, pathToHistory);
        System.out.println("Рекомендуем к просмотру: " + onlineCinema.findRecommendation(userMovie));
    }
}
