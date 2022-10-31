package ru.croc.task7;

import java.util.Scanner;
import ru.croc.task7.exeptions.*;

public class Task7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] customPositions = scanner.nextLine().split(" ");
        // Объявляем массив шахматных позиций
        ChessPosition[] positions = new ChessPosition[customPositions.length];

        try {

            // Инициализируем массив шахматных позиций и создаем их
            for (int i = 0; i < customPositions.length; i++) {
                positions[i] = ChessPosition.parse(customPositions[i]);
            }

            // Проверяем может ли так двигаться конь (Если не может появится исключение)
            for (int j = 0; j < positions.length - 1; j++) {
                move(positions[j], positions[j + 1]);
            }

            // Если не возникло ошибок, значит движение возможно
            System.out.println("OK");

        } catch (IllegalPositionException | IllegalMoveException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * Движение коня
     */
    public static void move(ChessPosition chessPositionOut, ChessPosition chessPositionIn) throws IllegalMoveException {

        // Движение коня по гипотенузе прямоугольного трегольника
        int dy = chessPositionIn.getPositionY() - chessPositionOut.getPositionY();
        int dx = chessPositionIn.getPositionX() - chessPositionOut.getPositionX();

        // Если гипотенуза не равна корню из 5 - ход не верный
        if (Math.pow(dy, 2) + Math.pow(dx, 2) != 5) {
            throw new IllegalMoveException(chessPositionOut, chessPositionIn);
        }

    }

}