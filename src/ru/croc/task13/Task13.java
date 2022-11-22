package ru.croc.task13;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Task13 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите вашу историю просмтров: ");
        String userMovie = scanner.nextLine();

        String pathToMovies = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task13/files/movies.txt";
        String pathToHistory = "/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task13/files/movieWatchHistory.txt";

        Map<Integer, String> moviesToWatch = readListOfAvailableMovies(pathToMovies);
        List<List<Integer>> allUsersMovieHistory = readMovieWatchHistoryDatabase(pathToHistory);
        List<Integer> userMovieHistory = movieWatchHistoryParser(userMovie);

        int recommendationFilm = findMovieRecommendations(userMovieHistory, allUsersMovieHistory);
        if (recommendationFilm != 0) {
            System.out.println(moviesToWatch.get(recommendationFilm));
        } else {
            System.out.println("Увы! Рекомендации отсутствуют!");
        }

    }

    /**
     * Поиск рекомендаций фильмов для пользователя
     * @param userMovieHistory просмотренные фильмы пользователем
     * @param allUsersMovieHistory база данных просмотров всех пользователей
     * @return код рекомендуемого фильма
     */
    public static int findMovieRecommendations(List<Integer> userMovieHistory,
            List<List<Integer>> allUsersMovieHistory) {

        // Если не найдется фильм для рекомендаций то выводим ноль
        int recommendationMovieIndex = 0;

        // Находим все рекомендуемые фильмы для пользователя, которые он не смотрел
        Set<Integer> allRecommendationMovie = new HashSet<>();
        for (List<Integer> oneUserHistory : allUsersMovieHistory) {
            for (int movie : oneUserHistory) {
                if (userMovieHistory.contains(movie)) {
                    allRecommendationMovie.addAll(oneUserHistory);
                    break;
                }
            }
        }
        allRecommendationMovie.removeAll(userMovieHistory);

        // Ищем самый просматриваемый фильм пользователем
        Map<Integer, Integer> repeatViews = new HashMap<>();
        for (int i : userMovieHistory) {
            Integer count = repeatViews.get(i);
            repeatViews.put(i, count != null ? count + 1 : 1);
        }
        int bestMovie = Collections.max(repeatViews.values());

        // Выбираем наиболее подходящий для просмотра
        for (int recommendationMovie : allRecommendationMovie) {
            for (List<Integer> oneUserHistory : allUsersMovieHistory) {

                // Ищем самые популярные фильмы среди сходих пользователей
                repeatViews.clear();
                for (int i : oneUserHistory) {
                    Integer count = repeatViews.get(i);
                    repeatViews.put(i, count != null ? count + 1 : 1);
                }
                int bestUserMovie = Collections.max(repeatViews.values());

                // Если самые популярные схожи то выдаем из фильмов этого человека
                if (oneUserHistory.contains(recommendationMovie) && bestUserMovie == bestMovie) {
                    recommendationMovieIndex = recommendationMovie;
                    return recommendationMovieIndex;
                }
            }
            // Если не было людей которые так же чаще всего смотрят тот же фильм что и пользователь выдаем людой из их просмотренных
            recommendationMovieIndex = recommendationMovie;
        }
        return recommendationMovieIndex;
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
    public static List<List<Integer>> readMovieWatchHistoryDatabase(String path)
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
     * Преобразовать строку с просмотренными фильмами в List
     * @param line строка с историей просмтров
     * @return List истории прсомтров
     */
    public static List<Integer> movieWatchHistoryParser(String line) {
        List<Integer> movieHistory = new ArrayList<>();
        line = line.replaceAll("[^0-9,]", "");
        String[] allFilmsArray = line.split(",");
        for (String film : allFilmsArray) {
            movieHistory.add(Integer.parseInt(film));
        }
        return movieHistory;
    }

}
