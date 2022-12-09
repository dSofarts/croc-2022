package ru.croc.task18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.h2.Driver;

public class Task18 {

    static final String DATABASE = "jdbc:h2:tcp://localhost/~/test";
    static final String DRIVER = "org.h2.Driver";
    static final String USER = "sa";
    static final String PASSWORD = "sa";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        List<Product> products = new ArrayList<>();
        products.add(new Product("T1", "Монитор", 500));
        products.add(new Product("T2", "Мышь", 50));

        Class.forName(DRIVER);
        Driver driver = new org.h2.Driver();
        try (Connection connection = DriverManager.getConnection(DATABASE, USER, PASSWORD)) {
            DaoProduct dao = new DaoProduct();
            dao.setConnection(connection);
            System.out.println(dao.findProduct("Egor"));
            dao.closeConnections();
        }

    }

}
