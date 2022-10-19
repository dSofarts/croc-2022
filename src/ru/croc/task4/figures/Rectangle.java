package ru.croc.task4.figures;

public class Rectangle extends Figure {

    private int x1;
    private int y1;
    private int x2;
    private int y2;


    /**
     * Конструктор прямоугольника
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public String getInformation() {
        return "R (" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + "): ";
    }
}
