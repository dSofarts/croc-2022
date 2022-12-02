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

public class OnlineCinema {

    private Map<Integer, String> films;
    private List<List<Integer>> allUsersHistory;

    public OnlineCinema(Map<Integer, String> films, List<List<Integer>> allUsersHistory) {
        this.films = films;
        this.allUsersHistory = allUsersHistory;
    }

    /**
     * Создать кинотеатр из путей к файлам
     * @param pathToFilmsFile путь к файлу с всеми фильмами
     * @param pathToHistory путь к файлу с историей просмотров пользователей
     * @return новый кинотеатр
     */
    public static OnlineCinema createFromFiles(String pathToFilmsFile, String pathToHistory)
            throws IOException {
        return new OnlineCinema(readAllFilmsFile(pathToFilmsFile),
                readAllUsersHistoryFile(pathToHistory));
    }

    /**
     * Поиск рекомендаций
     * @param userMovie история просмотров пользователя
     * @return рекомендуемый фильм
     */
    public String findRecommendation(String userMovie) {
        List<Integer> userHistory = movieWatchHistoryParser(userMovie);
        List<List<Integer>> similarUsers = findSimilarUsers(userHistory);

        HashMap<Integer, Integer> numberOfMovieViews = new HashMap<>();
        for (List<Integer> user : similarUsers) {
            for (Integer film : user) {
                Integer oldCount = numberOfMovieViews.get(film);
                if (oldCount == null) {
                    oldCount = 0;
                }
                numberOfMovieViews.put(film, oldCount + 1);
            }
        }
        if (numberOfMovieViews.size() == 0) {
            return "рекомендаций нет!";
        }
        int recommendationFilm = Collections.max(numberOfMovieViews.entrySet(),
                Map.Entry.comparingByValue()).getKey();
        return films.get(recommendationFilm);
    }

    /**
     * Найти похожих пользователей
     * @param user история просмотров пользователя
     * @return похожие истории просмотров (без фильмов, которые уже просмотрены)
     */
    private List<List<Integer>> findSimilarUsers(List<Integer> user) {
        List<List<Integer>> similarUsers = new ArrayList<>();
        for (List<Integer> userHistory : allUsersHistory) {
            int similarFilms = 0;
            for (Integer film : user) {
                if (userHistory.contains(film)) {
                    similarFilms++;
                }
            }
            if (similarFilms != 0 && similarFilms >= (user.size() / 2)) {
                userHistory.removeAll(user);
                similarUsers.add(userHistory);
            }
        }
        return similarUsers;
    }

    /**
     * Прочитать файл с всеми фильмами в прокате и вывести HashMap с этими фильмами
     * @param path путь к файлу
     * @return HashMap со всеми фильмами в прокате
     */
    private static Map<Integer, String> readAllFilmsFile(String path) throws IOException {
        Map<Integer, String> availableMovies = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
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
     * Прочитать файл с историей просмотров всех пользователей и вывести List с этой историей
     * @param path путь к файлу
     * @return List со всей историей просмотров
     */
    private static List<List<Integer>> readAllUsersHistoryFile(String path)
            throws IOException {
        List<List<Integer>> movieWatchHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String history;
            while ((history = reader.readLine()) != null) {
                movieWatchHistory.add(movieWatchHistoryParser(history));
            }
        }
        return movieWatchHistory;
    }

    /**
     * Преобразовать строку с фильмами в List
     * @param line строка с историей просмотров
     * @return List истории просмотров
     */
    private static List<Integer> movieWatchHistoryParser(String line) {
        List<Integer> movieHistory = new ArrayList<>();
        line = line.replaceAll("[^0-9,]", "");
        String[] allFilmsArray = line.split(",");
        for (String film : allFilmsArray) {
            movieHistory.add(Integer.parseInt(film));
        }
        return movieHistory;
    }
}
