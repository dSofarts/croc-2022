package ru.croc.task9.exeptions;

import java.io.IOException;
import java.nio.file.Path;

public class IncorrectPathException extends IOException {

    private Path path;

    public IncorrectPathException(Path path) {
        this.path = path;
    }

    @Override
    public String getMessage() {
        return "Incorrect path: " + path.toString();
    }
}
