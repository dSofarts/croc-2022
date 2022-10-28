package ru.croc.task7;

import java.util.Arrays;
import java.util.Scanner;
import ru.croc.task7.exeptions.IllegalMoveException;
import ru.croc.task7.exeptions.IllegalPositionException;

public class Task7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] customPositions = scanner.nextLine().split(" ");
        ChessPosition[] positions = new ChessPosition[customPositions.length];
        try {
            for (int i = 0; i < customPositions.length; i++) {
                positions[i] = ChessPosition.parse(customPositions[i]);
            }
            for (int j = 0; j < positions.length - 1; j++) {
                move(positions[j], positions[j + 1]);
            }
            System.out.println("OK");
        } catch (IllegalPositionException | IllegalMoveException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void move(ChessPosition chessPositionOut, ChessPosition chessPositionIn) throws IllegalMoveException {
        int dy = chessPositionIn.getPositionY() - chessPositionOut.getPositionY();
        int dx = chessPositionIn.getPositionX() - chessPositionOut.getPositionX();
        if (Math.pow(dy, 2) + Math.pow(dx, 2) != 5) {
            throw new IllegalMoveException(chessPositionOut.toString(), chessPositionIn.toString());
        }
    }

}
