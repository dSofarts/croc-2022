package ru.croc.task7.exeptions;

public class IllegalPositionException extends Exception {

    @Override
    public String getMessage() {
        return "Incorrect position value";
    }

}
