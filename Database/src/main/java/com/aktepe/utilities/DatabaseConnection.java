package com.aktepe.utilities;

import java.sql.*;

public class DatabaseConnection {
    private static Connection connection;
    private static String connectionString;
    private static String brand = ConfigurationReader.getProperty("brand");
    private static String host = ConfigurationReader.getProperty("host");
    private static String username = ConfigurationReader.getProperty("username");
    private static String password = ConfigurationReader.getProperty("password");
    private static String database = ConfigurationReader.getProperty("database");

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
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}