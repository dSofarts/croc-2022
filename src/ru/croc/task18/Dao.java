package ru.croc.task18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Dao {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnections() throws SQLException {
        connection.close();
    }

    public void createProduct(Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO PRODUCT VALUES(?,?,?)")) {
            statement.setString(1, product.getArticle());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.execute();
        }
    }

    public Product findProduct(String productCode) throws SQLException {
        Product product = new Product();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT p WHERE p.ARTICLE = ?")) {
            statement.setString(1, productCode);
            try (ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    product.setArticle(productCode);
                    product.setName(result.getString("NAME"));
                    product.setPrice(result.getInt("PRICE"));
                }
            }
        }
        return product;
    }

    public void updateProduct(Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE PRODUCT SET NAME=? AND PRICE=? WHERE ARTICLE=?;")) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getArticle());
            statement.execute();
        }
    }

    public void deleteProduct(String productCode) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ARTICLE=?;")) {
            statement.setString(1, productCode);
            statement.execute();
        }
    }

    public Order createOrder(String userLogin, List<Product> products) throws SQLException {
        int orderNumber = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(NUMBER) FROM \"ORDER\";")) {
            try (ResultSet result = statement.executeQuery()){
                while (result.next()) {
                    orderNumber = result.getInt("MAX(NUMBER)") + 1;
                }
            }
        }
        for (Product product : products) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO \"ORDER\" VALUES(?, ?,?)")) {
                statement.setInt(1, orderNumber);
                statement.setString(2, userLogin);
                statement.setString(3, product.getArticle());
                statement.execute();
            }
        }
        return new Order(orderNumber, userLogin, products);
    }
}