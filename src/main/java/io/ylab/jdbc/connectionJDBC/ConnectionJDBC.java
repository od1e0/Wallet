package io.ylab.jdbc.connectionJDBC;

import java.sql.*;

public class ConnectionJDBC {
    private static final String URL = "jdbc:postgresql://localhost:8888/jdbc";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "raat";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT count(*) FROM information_schema.tables;")) {
            while (resultSet.next()) {
                int tablesCount = resultSet.getInt("count");
                System.out.println("There is " + tablesCount + " tables in database");
            }
        } catch (SQLException exception) {
            System.out.println("Got SQL Exception " + exception.getMessage());
        }
    }
}
