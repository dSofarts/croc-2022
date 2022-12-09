package ru.croc.task18.exceptions;

import java.io.IOException;

public class ArticleNotFreeException extends IOException {

    private String article;

    public ArticleNotFreeException(String article) {
        this.article = article;
    }

    public String getMessage() {
        return "The article \"" + article + "\" is not free";
    }

}
