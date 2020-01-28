package com.aktepe.test;

import com.aktepe.utilities.DatabaseDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTesting {
    private Connection connection = null;
    private Statement statement;

    @BeforeTest
    public void setUp() {
        try {
            // Get connection to DB
            connection = DatabaseDriver.get();

            // Statement object to send the SQL statement to the Database
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
    public void testQueryShowEmployees() {
        try {
            // Query String
            String queryForAllEmployees = "SELECT * FROM EMPLOYEES";

            // Get the contents of table from DB
            ResultSet res = statement.executeQuery(queryForAllEmployees);

            // Print the all result
            while (res.next()) {
                int employeeID = res.getInt(1); // getInt(ColumnLabel) also possible
                String employeeFirstName = res.getString(2);
                String employeeLastName = res.getString(3);
                String employeeEmail = res.getString(4);
                String employeePhoneNumber = res.getString(5);
                String employeeHireDate = res.getString(6);
                String employeeJobID = res.getString(7);
                int employeeSalary = res.getInt(8);
                int employeeManagerID = res.getInt(9);
                int employeeDepartmentID = res.getInt(10);

                System.out.printf(
                        "%d \t %-10s \t %-10s \t %-10s \t %-10s \t %-15s \t %-10s \t %d \t %d \t %d \n",
                        employeeID,
                        employeeFirstName,
                        employeeLastName,
                        employeeEmail,
                        employeePhoneNumber,
                        employeeHireDate,
                        employeeJobID,
                        employeeSalary,
                        employeeManagerID,
                        employeeDepartmentID
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
    public void testQueryInsertEmployee() {
        try {
            // Query String
            String queryInsertEmployee = "INSERT INTO EMPLOYEES VALUES(207, 'Emrah', 'Aktepe', 'emrahaktepe@gmail.com', '650.505.3876', TO_DATE('2020-03-01','YYYY-MM-DD'), 'IT_PROG', 12000.00, 0.30, 103, 60)";

            // Insert data value
            statement.executeUpdate(queryInsertEmployee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
    public void testQueryUpdateEmployee() {
        try {
            // Query String
            String queryInsertEmployee = "UPDATE EMPLOYEES SET SALARY=14000.00 WHERE EMPLOYEE_ID=207";

            // Update data value
            statement.executeUpdate(queryInsertEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = true)
    public void testQueryShowEmployeeByID() {
        try {
            // Query String
            String querySelectEmployeeByID = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID=207";

            // Get the contents of table from DB
            ResultSet res = statement.executeQuery(querySelectEmployeeByID);

            res.next();
            int employeeSalary = res.getInt("SALARY");

            Assert.assertEquals(employeeSalary, 14000.00);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Closing Database Connection...");
        DatabaseDriver.close();
    }
}
