package ru.croc.task7;

import ru.croc.task7.exeptions.IllegalPositionException;

public class Task7 {

    public static void main(String[] args) {

        try {
            ChessPosition chessPosition = new ChessPosition(5, 1);
            System.out.println(chessPosition.toString());
        } catch (IllegalPositionException e) {
            e.getMessage();
        }

    }

}
