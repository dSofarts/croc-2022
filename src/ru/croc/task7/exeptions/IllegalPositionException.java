package ru.croc.task7.exeptions;

public class IllegalPositionException extends Exception {

    private String position;

    public IllegalPositionException(String position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "Некорректная позиция : " + "\"" + position + "\"";
    }

}
