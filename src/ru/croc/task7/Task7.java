package ru.croc.task7;

import java.util.Scanner;
import ru.croc.task7.exeptions.*;

public class Task7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] customPositions = scanner.nextLine().split(" ");
        ChessPosition[] positions = new ChessPosition[customPositions.length];
        try {

            // Создаем позиции на доске
            for (int i = 0; i < customPositions.length; i++) {
                positions[i] = ChessPosition.parse(customPositions[i]);
            }

            // Проверяем можели ли так двигаться ход
            for (int j = 0; j < positions.length - 1; j++) {
                move(positions[j], positions[j + 1]);
            }
            // если не возникло ошибок, значит движение возможно
            System.out.println("OK");

        } catch (IllegalPositionException | IllegalMoveException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Движение коня
     * @param chessPositionOut
     * @param chessPositionIn
     * @throws IllegalMoveException
     */
    public static void move(ChessPosition chessPositionOut, ChessPosition chessPositionIn) throws IllegalMoveException {

        // движение коня по гипотенузе прямоугольного трегольника
        int dy = chessPositionIn.getPositionY() - chessPositionOut.getPositionY();
        int dx = chessPositionIn.getPositionX() - chessPositionOut.getPositionX();

        if (Math.pow(dy, 2) + Math.pow(dx, 2) != 5) {
            throw new IllegalMoveException(chessPositionOut, chessPositionIn);
        }

    }

}