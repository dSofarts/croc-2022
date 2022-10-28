package ru.croc.task7;

import java.util.Arrays;
import ru.croc.task7.exeptions.IllegalPositionException;

public class Task7 {

    public static void main(String[] args) {

        try {
            ChessPosition chessPosition = ChessPosition.parse("a1s");
            System.out.println(chessPosition.toString());
        } catch (IllegalPositionException e) {
            System.err.println(e.getMessage());
        }

    }

}
