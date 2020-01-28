package com.aktepe.tests;

import com.aktepe.pages.LoginPage;
import com.aktepe.utilities.ConfigReader;
import com.aktepe.utilities.SeleniumDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginPageTest {
    private WebDriver driver;

    @BeforeTest
    public void init() {
        driver = SeleniumDriver.get();
    }

    @Test
    public void testLoginBestScenario() {
        driver.get(ConfigReader.getProperty("url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("email"),ConfigReader.getProperty("password") );
    }

    @Test
    public void testLoginWithWrongEmail() {
        driver.get(ConfigReader.getProperty("url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getProperty("email"),ConfigReader.getProperty("password") );
        System.out.println(driver.getCurrentUrl());

        if (driver.getCurrentUrl().equals("http://seolyst.com/")) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }

    @Test
    public void testForgotPassword() {
        driver.get(ConfigReader.getProperty("url"));
        LoginPage loginPage = new LoginPage();
        loginPage.clickForgotPassword();

        Assert.assertEquals(driver.getCurrentUrl(), "http://seolyst.com/accounts/password_rest/");
    }

    @AfterTest
    public void end() {
        driver.quit();
    }
}
