package ru.croc.task17;

import org.h2.Driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class Task17 {

    static final String DATABASE = "jdbc:h2:tcp://localhost/~/test";
    static final String DRIVER = "org.h2.Driver";
    static final String USER = "sa";
    static final String PASSWORD = "sa";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(DRIVER);
        Driver driver = new org.h2.Driver();
        try (Connection connection = DriverManager.getConnection(DATABASE, USER, PASSWORD)){
            createTables(connection);
            initializationTable(args[0], connection);
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Создание таблиц
     * @param connection соединение c БД
     */
    public static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()){
            String SQL = "CREATE TABLE \"ORDER\"(NUMBER INT, LOGIN VARCHAR(255), ARTICLE VARCHAR(255));\nCREATE TABLE \"ITEM\"(ARTICLE VARCHAR(255) PRIMARY KEY, NAME VARCHAR(255), PRICE INTEGER);".formatted();
            statement.execute(SQL);
        }
    }

    /**
     * Добавление в таблицу
     * @param connection соединение с БД
     * @param tableName Имя таблицы
     * @param attributes в какие ячейки
     * @param first первое значение
     * @param second второе значение
     * @param third третье значение
     */
    public static void update(Connection connection, String tableName, String attributes, String first, String second, String third) throws SQLException {
        try (Statement statement = connection.createStatement()){
            String SQL = "INSERT INTO %s%s VALUES(%s, %s, %s);".formatted(tableName, attributes, first, second, third);
            statement.execute(SQL);
        }
    }

    /**
     * Проверка есть ли артикул в БД
     * @param connection соединение с БД
     * @param article артикул
     * @return наличие артикула
     */
    public static boolean haveArticle(Connection connection, String article) throws SQLException {
        String SQL = "SELECT * FROM ITEM WHERE ARTICLE = %s;".formatted(article);
        try (Statement statement = connection.createStatement()){
            try (ResultSet result = statement.executeQuery(SQL)) {
                return result.next();
            }
        }
    }

    /**
     * Инициализация таблиц
     * @param pathCSV путь к CSV файлу
     * @param connection соединение с БД
     */
    public static void initializationTable(String pathCSV, Connection connection) throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(pathCSV));
        String line = reader.readLine();
        while (line != null) {
            String[] cells = line.split(",");
            update(connection, "\"ORDER\"", " (NUMBER, LOGIN, ARTICLE)", cells[0], "'" + cells[1] + "'","'" +  cells[2] + "'");
            if(!haveArticle(connection, "'" + cells[2] + "'")) {
                update(connection, "ITEM",  " (ARTICLE, NAME, PRICE)", "'" + cells[2] + "'","'" +  cells[3] + "'", cells[4]);
            }
            line = reader.readLine();
        }
    }
}