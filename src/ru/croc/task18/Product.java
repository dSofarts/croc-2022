package ru.croc.task18;

public class Product {

    private String article;
    private String name;
    private int price;

    public Product() {

    }

    public Product(String article, String name, int price) {
        this.article = article;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }
}
