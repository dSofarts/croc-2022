package ru.croc.task6;

import ru.croc.task6.figures.Circle;
import ru.croc.task6.figures.Figure;
import ru.croc.task6.figures.Rectangle;

public class Task6 {

    public static void main(String[] args) {

        Figure rectangle = new Rectangle(70, 70, 150, 200);
        Figure circle = new Circle(80, 80, 10);

        Annotation annotation1 = new Annotation(circle, "Tree");
        Annotation annotation2 = new Annotation(rectangle, "Car");

        AnnotatedImage annotatedImage = new AnnotatedImage("/Desktop/img.jpg", annotation1, annotation2);

        try {

            annotatedImage.findByLabel("Tree").getFigure().move(50, 50);
            annotatedImage.findByPoint(150, 200).getFigure().move(120, 140);

            for (Annotation annotation : annotatedImage.getAnnotations()) {
                System.out.println(annotation.toString());
            }

        } catch (NullPointerException ex) {
            System.out.println("Нет фигур, которые подходят под условие");
        }

    }
}
