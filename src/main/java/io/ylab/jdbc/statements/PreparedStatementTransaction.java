package io.ylab.jdbc.statements;

import java.math.BigDecimal;
import java.sql.*;

public class PreparedStatementTransaction {

    private static final String URL = "jdbc:postgresql://localhost:8888/jdbc";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "raat";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD)) {
            createTable(connection);
            insertRecord(connection);
            ResultSet resultSet = retrieveTransactions(connection);
            printTransactions(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printTransactions(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String operation = resultSet.getString("operation");
            BigDecimal amount = resultSet.getBigDecimal("amount");
            System.out.println("ID: " + id + ", Username: " + username + ", Operation: " + operation + ", Amount: " + amount);
        }
    }

    private static ResultSet retrieveTransactions(Connection connection) throws SQLException {
        String retrieveDataSQL = "SELECT * FROM private.transactions";
        Statement retrieveDataStatement = connection.createStatement();
        ResultSet resultSet = retrieveDataStatement.executeQuery(retrieveDataSQL);
        return resultSet;
    }

    private static void insertRecord(Connection connection) throws SQLException {
        String insertDataSQL = "INSERT INTO private.transactions (username, operation, amount) VALUES (?, ?, ?)";
        PreparedStatement insertDataStatement = connection.prepareStatement(insertDataSQL);
        insertDataStatement.setString(1, "admin");
        insertDataStatement.setString(2, "credit");
        insertDataStatement.setBigDecimal(3, new BigDecimal(255.33));
        insertDataStatement.executeUpdate();
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS private.transactions (" +
                "id SERIAL PRIMARY KEY," +
                "username VARCHAR(255)," +
                "operation VARCHAR(255)," +
                "amount DECIMAL(9,2))";
        Statement createTableStatement = connection.createStatement();
        createTableStatement.execute(createTableSQL);
    }
}
