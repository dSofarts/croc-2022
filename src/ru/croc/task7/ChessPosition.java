package ru.croc.task7;

import java.lang.reflect.Array;
import java.util.Arrays;
import ru.croc.task7.exeptions.IllegalPositionException;

public class ChessPosition {

    private int positionX;
    private int positionY;
    private static String[] letterColumns = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public ChessPosition(int positionX, int positionY) throws IllegalPositionException {
        if (positionX < 0 || positionX > 7 || positionY < 0 || positionY > 7) {
            throw new IllegalPositionException();
        }
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static ChessPosition parse(String position) throws IllegalPositionException {
        try {
            String[] positions = position.split("");
            int positionX = Arrays.asList(letterColumns).indexOf(positions[0]);
            int positionY = Integer.parseInt(positions[1]) - 1;
            return new ChessPosition(positionX, positionY);
        } catch (Exception exception) {
            throw new IllegalPositionException();
        }
    }

    @Override
    public String toString() {
        return letterColumns[positionY] + (positionX + 1);
    }
}