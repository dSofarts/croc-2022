package ru.croc.task6;

import ru.croc.task6.figures.Circle;
import ru.croc.task6.figures.Figure;
import ru.croc.task6.figures.Rectangle;

public class Task6 {

    public static void main(String[] args) {

        Figure rectangle = new Rectangle(100, 100, 150, 200);
        Figure circle = new Circle(100, 100, 10);

        Annotation annotation1 = new Annotation(circle, "Tree");
        Annotation annotation2 = new Annotation(rectangle, "Car");

        AnnotatedImage annotatedImage = new AnnotatedImage("/Desktop/img.jpg", annotation1, annotation2);

        try {
            System.out.println(annotatedImage.findByPoint(110, 150).toString());
        } catch (NullPointerException ex) {
            System.out.println("Нет фигур содержащих данную точку");
        }

    }
}
