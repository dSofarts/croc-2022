package ru.croc.task4;

import ru.croc.task4.figures.Figure;

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
}
