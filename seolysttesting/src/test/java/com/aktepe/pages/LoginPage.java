package com.aktepe.pages;

import com.aktepe.utilities.SeleniumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "id_username")
    public WebElement emailTextInput;

    @FindBy(id = "id_password")
    WebElement passwordTextInput;

    @FindBy(id = "btn")
    WebElement loginButton;

    @FindBy(linkText = "Şifremi unuttum?")
    WebElement forgotPassword;

    public LoginPage() {
        PageFactory.initElements(SeleniumDriver.get(), this);
    }

    public void login(String email, String password) {
        try {
            emailTextInput.sendKeys(email);
            Thread.sleep(2000);
            passwordTextInput.sendKeys(password);
            Thread.sleep(2000);
            loginButton.click();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickForgotPassword() {
        try {
            forgotPassword.click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public boolean isError() {
//        if (errorMessage == null) {
//            return false;
//        }
//
//        return true;
//    }
}
