package ru.croc.task6;

public class AnnotatedImage {

    private final String imagePath;
    private final Annotation[] annotations;

    public AnnotatedImage(String imagePath, Annotation... annotations) {
        this.imagePath = imagePath;
        this.annotations = annotations;
    }

    /**
     * Возвращаем путь к изображению
     * @return
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Возвращение массива разметок
     * @return
     */
    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().findPointInFigure(x, y)) {
                return annotation;
            }
        }
        return null;
    }
}
