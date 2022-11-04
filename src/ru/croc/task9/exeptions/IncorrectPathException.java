package ru.croc.task9.exeptions;

import java.io.IOException;

public class IncorrectPathException extends IOException {

    private String path;

    public IncorrectPathException(String path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return "Incorrect path: " + path;
    }
}
