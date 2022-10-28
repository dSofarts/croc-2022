package ru.croc.task7;

import java.lang.reflect.Array;
import java.util.Arrays;
import ru.croc.task7.exeptions.IllegalPositionException;

public class ChessPosition {

    private int positionX;
    private int positionY;
    private static String[] letterColumns = {"a", "b", "c", "d", "e", "f", "g", "h"};

    private ChessPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public static ChessPosition parse(String position) throws IllegalPositionException {
        try {
            String[] positions = position.split("");
            if (positions.length != 2) {
                throw new Exception();
            }
            int positionY = Arrays.asList(letterColumns).indexOf(positions[0]);
            int positionX = Integer.parseInt(positions[1]) - 1;
            return new ChessPosition(positionX, positionY);
        } catch (Exception exception) {
            throw new IllegalPositionException(position);
        }
    }

    @Override
    public String toString() {
        return letterColumns[positionY] + (positionX + 1);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

}