package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Task13 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите вашу историю просмтров: ");
        String userMovieHistory = scanner.nextLine();
        String pathToMovies = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task13/files/movies.txt";
        String pathToHistory = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task13/files/movieWatchHistory.txt";

        Map<Integer, String> availableMovies = readListOfAvailableMovies(pathToMovies);
        List<List<Integer>> historyMovies = readMovieWatchHistoryDatabase(pathToHistory);
        List<Integer> history = movieWatchHistoryParser(userMovieHistory);

        System.out.println(Collections.singletonList(history));
        System.out.println(Collections.singletonList(availableMovies));
        System.out.println(Collections.singletonList(historyMovies));

    }

    /**
     * Прочитать файл с всеми фильмами в прокате и вывести HashMap с этими фильмами
     * @param path путь к файлу
     * @return HashMap со всеми фильмами в прокате
     */
    public static Map<Integer, String> readListOfAvailableMovies(String path) throws IOException {
        Map<Integer, String> availableMovies = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String film;
            while ((film = reader.readLine()) != null) {
                String[] filmAndIndex = new String[2];
                filmAndIndex[0] = film.substring(0, film.indexOf(","));
                filmAndIndex[1] = film.substring(film.indexOf(",") + 1);
                availableMovies.put(Integer.parseInt(filmAndIndex[0]), filmAndIndex[1]);
            }
        }
        return availableMovies;
    }

    /**
     * Прочитать файл с историей просмтров всех пользователей и вывести List с этой историей
     * @param path путь к файлу
     * @return List со всей историей просмотров
     */
    public static List<List<Integer>> readMovieWatchHistoryDatabase(String path) throws IOException {
        List<List<Integer>> movieWatchHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String history;
            while ((history = reader.readLine()) != null) {
                movieWatchHistory.add(movieWatchHistoryParser(history));
            }
        }
        return movieWatchHistory;
    }

    /**
     * Преобразовать строку с просмотренными фильмами в List
     * @param line строка с историей просмтров
     * @return List истории прсомтров
     */
    public static List<Integer> movieWatchHistoryParser(String line) {
        List<Integer> movieHistory = new ArrayList<>();
        line = line.replaceAll("[^0-9,]", "");
        String[] allFilmsArray = line.split(",");
        for (int i = 0; i < allFilmsArray.length; i++) {
            movieHistory.add(Integer.parseInt(allFilmsArray[i]));
        }
        return movieHistory;
    }

}
