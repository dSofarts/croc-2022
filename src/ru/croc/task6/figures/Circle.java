package ru.croc.task6.figures;

public class Circle extends Figure {

    private int x1;
    private int y1;
    private int radius;

    /**
     * Конструктор окружностей
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
        // позиционируем точку по отношению к окружности так, будто середина окружности это начало координат
        int x = dx - x1;
        int y = dy - y1;
        // проверяем есть ли точка в окружности
        return (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(radius, 2));
    }

    @Override
    public void move(int dx, int dy) {
        this.x1 += dx;
        this.y1 += dy;
    }
}
