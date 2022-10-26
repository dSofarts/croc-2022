package ru.croc.task6.figures;

public abstract class Figure {

    /**
     * Врзвращает информацию о вложенном объекте
     * @return
     */
    abstract public String getInformation();

    abstract public boolean findPointInFigure(int dx, int dy);
}
