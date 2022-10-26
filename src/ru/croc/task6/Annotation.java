package ru.croc.task6;

import ru.croc.task6.figures.Figure;

public class Annotation {

    private String signature;
    private Figure figure;

    /**
     * Конструктор разменти
     * @param figure
     * @param signature
     */
    public Annotation(Figure figure, String signature) {
        this.signature = signature;
        this.figure = figure;

    }

    @Override
    public String toString() {
        return this.figure.getInformation() + this.signature;
    }

    public Figure getFigure() {
        return figure;
    }

    public String getSignature() {
        return signature;
    }
}
