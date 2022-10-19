package ru.croc.task4;

import ru.croc.task4.figures.*;

public class Task4 {

    public static void main(String[] args) {

        Figure rectangle = new Rectangle(100, 100, 150, 200);
        Figure circle = new Circle(100, 100, 10);

        Annotation annotation1 = new Annotation(circle, "Tree");
        Annotation annotation2 = new Annotation(rectangle, "Car");

        AnnotatedImage annotatedImage = new AnnotatedImage("/Desktop/img.jpg", annotation1, annotation2);

        for (Annotation annotation : annotatedImage.getAnnotations()) {
            System.out.println(annotation.toString());
        }

    }
}
