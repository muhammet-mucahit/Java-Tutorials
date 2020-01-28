package com.aktepe.utilities;

import java.sql.*;

public class DatabaseDriver {
    private static Connection connection;
    private static String connectionString;
    private static String brand = ConfigReader.getProperty("brand");
    private static String host = ConfigReader.getProperty("host");
    private static String username = ConfigReader.getProperty("username");
    private static String password = ConfigReader.getProperty("password");
    private static String database = ConfigReader.getProperty("database");

    public static Connection get() {
        try {
            // To lower case for comparing truly
            brand = brand.toLowerCase();

            // Load the driver class
            switch (brand) {
                case "oracle":
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    connectionString = String.format("jdbc:oracle:thin:@%s:1521:XE", host);
                    break;
                case "mysql":
                    Class.forName("com.mysql.jdbc.Driver");
                    connectionString = String.format("jdbc:mysql://%s:3306/%s", host, database);
                    break;
                default:
                    throw new Exception("There is no brand like " + brand);
            }
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }

        return connection;
    }

    public static void close() {
        try {
            // Close DB connection
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}