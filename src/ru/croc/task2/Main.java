package ru.croc.task2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double numberByte = scanner.nextDouble();
        printByte(numberByte);
    }

    private static void printByte(double numberByte) {

        int unitTypeNumber = 1;
        String unitType;

        while (numberByte >= 1024) {
            numberByte /= 1024;
            unitTypeNumber++;
        }

        switch (unitTypeNumber) {
            case 2:
                unitType = " KB";
                break;
            case 3:
                unitType = " MB";
                break;
            case 4:
                unitType = " GB";
                break;
            case 5:
                unitType = " TB";
                break;
            default:
                unitType = " B";
        }

        System.out.println(String.format("%.1f", numberByte) + unitType);

    }
}
