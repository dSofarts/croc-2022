package ru.croc.task1;

import java.util.Scanner;

public class Task1 {

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

        // Вычисление длин сторон
        double lengthAB = lengthCalculation(a, b);
        double lengthBC = lengthCalculation(b, c);
        double lengthAC = lengthCalculation(a, c);

        // Вычисление полупериметра
        double halfPerimeter = (lengthAB + lengthBC + lengthAC) / 2;

        // Вычисление площади по формуле Герона
        double square = Math.sqrt(halfPerimeter * (halfPerimeter - lengthAB) * (halfPerimeter - lengthBC) * (halfPerimeter - lengthAC));

        System.out.println("Площадь треугольника: " + String.format("%.1f", square));
    }

    // Вычислекние длины стороны между точками a и b
    private static double lengthCalculation(Point a, Point b) {

        return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
    }
}
