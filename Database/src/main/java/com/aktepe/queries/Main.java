package com.aktepe.queries;

import com.aktepe.utilities.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.get();

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM COUNTRIES");

            while (rs.next())
                System.out.println(rs.getString("COUNTRY_ID") + " - " + rs.getString("COUNTRY_NAME"));

            DatabaseConnection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
