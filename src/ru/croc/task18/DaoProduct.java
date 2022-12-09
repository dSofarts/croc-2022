package ru.croc.task18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ru.croc.task18.exceptions.ArticleNotFreeException;

public class DaoProduct {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnections() throws SQLException {
        connection.close();
    }

    /**
     * Добавить продукт в БД
     * @param product продукт
     * @return продукт
     * @throws ArticleNotFreeException данный артикул уже присутствует в БД
     */
    public Product createProduct(Product product) throws SQLException, ArticleNotFreeException {
        if (findProduct(product.getArticle()) != null) {
            throw new ArticleNotFreeException(product.getArticle());
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PRODUCT VALUES(?,?,?)")) {
            statement.setString(1, product.getArticle());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.execute();
        }
        return product;
    }

    /**
     * Найти продукт в БД
     * @param productCode артикул продукта
     * @return продукт
     */
    public Product findProduct(String productCode) throws SQLException {
        Product product = new Product();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PRODUCT p WHERE p.ARTICLE = ?")) {
            statement.setString(1, productCode);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    product.setArticle(productCode);
                    product.setName(result.getString("NAME"));
                    product.setPrice(result.getInt("PRICE"));
                }
            }
        }
        if (product.getArticle() == null) {
            return null;
        }
        return product;
    }

    /**
     * Изменить продукт в БД
     * @param product продукт
     */
    public void updateProduct(Product product) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE PRODUCT SET NAME=? AND PRICE=? WHERE ARTICLE=?;")) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getArticle());
            statement.execute();
        }
    }

    /**
     * Удалить продукт из БД
     * @param productCode артикул продукта
     */
    public void deleteProduct(String productCode) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM PRODUCT WHERE ARTICLE=?;")) {
            statement.setString(1, productCode);
            statement.execute();
        }
    }
}