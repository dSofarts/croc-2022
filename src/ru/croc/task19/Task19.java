package ru.croc.task19;

import java.io.FileOutputStream;
import java.io.IOException;

public class Task19 {

    public static void main(String[] args) throws IOException {

        String text = "Hello, world!";
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/dsofarts/IdeaProjects/croc-2022/src/ru/croc/task19/test.txt");
        fileOutputStream.write(text.getBytes());
        fileOutputStream.close();

    }

}
