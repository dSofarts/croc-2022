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
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Возвращение массива разметок
     */
    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    /**
     * Поиск аннотации по точке
     */
    public Annotation findByPoint(int x, int y) {
        for (Annotation annotation : annotations) {
            if (annotation.getFigure().findPointInFigure(x, y)) {
                return annotation;
            }
        }
        return null;
    }

    /**
     * Поиск анотации по подписи
     */
    public Annotation findByLabel(String label) {
        for (Annotation annotation : annotations) {
            if (annotation.getSignature().contains(label)) {
                return annotation;
            }
        }
        return null;
    }
}
