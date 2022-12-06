package ru.croc.task18;

import java.util.List;

public class Order {

    private int number;
    private String login;
    private List<Product> products;

    public Order(int number, String login, List<Product> products) {
        this.number = number;
        this.login = login;
        this.products = products;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
