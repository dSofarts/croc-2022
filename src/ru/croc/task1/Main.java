package ru.croc.task1;

import java.util.Scanner;

public class Main {

    static class Point {
        double x;
        double y;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Point a = new Point();
        System.out.print("Введите координату x вершины №1: ");
        a.x = scanner.nextDouble();
        System.out.print("Введите координату y вершины №1: ");
        a.y = scanner.nextDouble();

        Point b = new Point();
        System.out.print("Введите координату x вершины №2: ");
        b.x = scanner.nextDouble();
        System.out.print("Введите координату y вершины №2: ");
        b.y = scanner.nextDouble();

        Point c = new Point();
        System.out.print("Введите координату x вершины №3: ");
        c.x = scanner.nextDouble();
        System.out.print("Введите координату y вершины №3: ");
        c.y = scanner.nextDouble();

        double lengthAB = lengthCalculation(a, b);
        double lengthBC = lengthCalculation(b, c);
        double lengthAC = lengthCalculation(a, c);
        double halfMeter = (lengthAB + lengthBC + lengthAC) / 2;
        double square = Math.sqrt(halfMeter * (halfMeter - lengthAB) * (halfMeter - lengthBC) * (halfMeter - lengthAC));

        System.out.println("Площадь треугольника: " + String.format("%.1f", square));

    }

    private static double lengthCalculation(Point a, Point b) {

        double length = Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
        return length;

    }
}
