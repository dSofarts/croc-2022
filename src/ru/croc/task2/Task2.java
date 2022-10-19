package ru.croc.task2;

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double numberByte = scanner.nextDouble();
        printByte(numberByte);
    }

    private static void printByte(double numberByte) {

        int unitTypeNumber = 0;
        String[] unitType = {" B", " KB", " MB", " GB", " TB"};

        while (numberByte >= 1024 && unitTypeNumber < (unitType.length - 1)) {
            numberByte /= 1024;
            unitTypeNumber++;
        }

        System.out.println(String.format("%.1f", numberByte) + unitType[unitTypeNumber]);
    }
}
