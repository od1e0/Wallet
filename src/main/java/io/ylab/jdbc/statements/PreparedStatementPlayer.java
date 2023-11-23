package io.ylab.jdbc.statements;

import java.math.BigDecimal;
import java.sql.*;

public class PreparedStatementPlayer {

    private static final String URL = "jdbc:postgresql://localhost:8888/jdbc";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "raat";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            createTable(connection);
            insertRecord(connection);
            ResultSet resultSet = retrieveUsers(connection);
            printUsers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printUsers(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            BigDecimal balance = resultSet.getBigDecimal("balance");
            System.out.println("ID: " + id + ", Username: " + username + ", Password: " + password + ", Balance: " + balance);
        }
    }

    private static ResultSet retrieveUsers(Connection connection) throws SQLException {
        String retrieveDataSQL = "SELECT * FROM private.users";
        Statement retrieveDataStatement = connection.createStatement();
        ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
        return resultSet;
    }

    private static void insertRecord(Connection connection) throws SQLException {
        String insertDataSQL = "INSERT INTO private.users (username, password, balance) VALUES (?, ?, ?)";
        PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setString(1, "admin");
        insertDataStatement.setString(2, "admin");
        insertDataStatement.setBigDecimal(3, new BigDecimal(1000.00));
        insertDataStatement.executeUpdate();
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS private.users (" +
                "id SERIAL PRIMARY KEY," +
                "username VARCHAR(255)," +
                "password VARCHAR(255)," +
                "balance DECIMAL)";
        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableSQL);
    }
}
