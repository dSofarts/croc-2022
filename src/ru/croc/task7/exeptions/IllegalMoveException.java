package ru.croc.task7.exeptions;

public class IllegalMoveException extends Exception {

    private String positionOut;
    private String positionIn;

    public IllegalMoveException(String positionOut, String positionIn) {
        this.positionOut = positionOut;
        this.positionIn = positionIn;
    }

    @Override
    public String getMessage() {
        return "Конь так не ходит: " + positionOut + " --> " + positionIn;
    }
}