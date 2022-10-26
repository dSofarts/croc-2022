package ru.croc.task6.figures;

public class Circle extends Figure {

    private int x1;
    private int y1;
    private int radius;

    /**
     * Конструктор окружностей
     * @param x1
     * @param y1
     * @param radius
     */
    public Circle(int x1, int y1, int radius) {
        this.x1 = x1;
        this.y1 = y1;
        this.radius = radius;
    }

    @Override
    public String getInformation() {
        return "C (" + x1 + ", " + y1 + "), " + radius + ": ";
    }

    @Override
    public boolean findPointInFigure(int dx, int dy) {
        return (x1 == dx && y1 == dy);
    }

    @Override
    public void move(int dx, int dy) {
        this.x1 = dx;
        this.y1 = dy;
    }
}
