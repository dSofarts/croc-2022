package ru.croc.task18;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoOrder {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void closeConnections() throws SQLException {
        connection.close();
    }

    /**
     * Создать заказ
     * @param userLogin пользователь
     * @param products купленные продукты
     * @return заказ
     */
    public Order createOrder(String userLogin, List<Product> products) throws SQLException {
        // Определяем максимальный номер заказа
        int orderNumber = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT MAX(NUMBER) FROM \"ORDER\";")) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    orderNumber = result.getInt("MAX(NUMBER)") + 1;
                }
            }
        }

        // Создаем заказ
        for (Product product : products) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"ORDER\" VALUES(?, ?,?)")) {
                statement.setInt(1, orderNumber);
                statement.setString(2, userLogin);
                statement.setString(3, product.getArticle());
                statement.execute();
            }
        }
        return new Order(orderNumber, userLogin, products);
    }
}
