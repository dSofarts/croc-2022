package ru.croc.task10;

public class PasswordCrackerParallel {

    public String countWordsParallel(int threadsNumber, String initialHash) throws InterruptedException {

        Thread[] threads = new Thread[threadsNumber];
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(new PasswordCracker(initialHash));
            threads[i].start();
        }
        return null;
    }

}
