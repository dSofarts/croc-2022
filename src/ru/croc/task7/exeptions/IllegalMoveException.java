package ru.croc.task7.exeptions;

import ru.croc.task7.ChessPosition;

public class IllegalMoveException extends Exception {

    private ChessPosition positionOut;
    private ChessPosition positionIn;


    public IllegalMoveException(ChessPosition positionOut, ChessPosition positionIn) {
        this.positionOut = positionOut;
        this.positionIn = positionIn;
    }

    @Override
    public String getMessage() {
        return "Конь так не ходит: " + positionOut.toString() + " --> " + positionIn.toString();
    }
}
