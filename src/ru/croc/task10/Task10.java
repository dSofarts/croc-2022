package ru.croc.task10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Task10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(calculatePassword(8, "C73BD86FC732B6E883FE9612F9BC5383"));
    }

    /**
     * Найти пароль
     * @param numberOfThreads колличество потоков
     * @param initialHash хеш пароля
     * @return пароль
     */
    public static String calculatePassword(int numberOfThreads, String initialHash)
            throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<String>> futures = new ArrayList<>(numberOfThreads);
        long max = max(7) - 1;
        long stepThread = max / numberOfThreads;

        // Запуск потоков
        for (int i = 0; i < numberOfThreads; i++) {
            futures.add(pool.submit(new PasswordCracker((stepThread * i) + 1, (stepThread * (i + 1)), initialHash)));
        }

        String password = "Пароль не найден!";
        for (Future<String> future : futures) {
            String futureResponse = future.get();
            if (!futureResponse.equals("")) {
                pool.shutdownNow();
                password = futureResponse;
                break;
            }
        }

        return password;
    }

    /**
     * Найти максимальное колличество комбинаций пароля
     * @param length длина пароля
     * @return максимальное колличество комбинаций пароля
     */
    public static long max(int length) {
        long max = 0;
        long radixPower = 1;
        for (int i = 0; i < length; i++) {
            radixPower = i == 0 ? 1 : radixPower * 26;
            max = max + radixPower;
        }
        return max * 26;
    }
}
