package ru.croc.task10;

public class Task10 {

    public static void main(String[] args) {

        int numberOfThreads = 5;

        Thread[] threads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(new PasswordCracker(i, numberOfThreads, 7));
            threads[i].start();
        }

    }
}
