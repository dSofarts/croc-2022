package ru.croc.task3;

import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] arrayString = scanner.nextLine().split(" ");
        int[] arrayInteger = new int[arrayString.length];

        for (int i = 0; i < arrayString.length; i++) {
            arrayInteger[i] = Integer.parseInt(arrayString[i]);
        }

        int max = 0;
        int min = 0;

        // Находим и переставляем минимальное число в массиве
        for (int i = 0; i < arrayInteger.length; i++) {
            if (arrayInteger[i] < arrayInteger[min]) {
                min = i;
            }
        }
        int temp = arrayInteger[0];
        arrayInteger[0] = arrayInteger[min];
        arrayInteger[min] = temp;

        // Находим и переставляем максимальное число в массиве
        for (int i = 0; i < arrayInteger.length; i++) {
            if (arrayInteger[i] > arrayInteger[max]) {
                max = i;
            }
        }
        temp = arrayInteger[arrayInteger.length - 1];
        arrayInteger[arrayInteger.length - 1] = arrayInteger[max];
        arrayInteger[max] = temp;

        // Выводим получившийся массив
        for (int i : arrayInteger) {
            System.out.print(i + " ");
        }
    }
}
