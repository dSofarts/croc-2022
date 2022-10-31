package ru.croc.task6.figures;

public abstract class Figure implements Movable {

    /**
     * Врзвращает информацию о вложенном объекте
     */
    abstract public String getInformation();

    /**
     * Поиск точки в фигуре
     */
    abstract public boolean findPointInFigure(int dx, int dy);
}
