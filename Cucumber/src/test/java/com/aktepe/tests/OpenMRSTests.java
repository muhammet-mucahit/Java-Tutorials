package com.aktepe.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.cucumber.stepdefs"},
        format =
                {"pretty",
                        "html:target/cucumber-reports/cucumber-pretty",
                        "json:target/cucumber-reports/CucumberTestReport.json",
                        "rerun:target/cucumber-reports/re-run.txt"}
)
public class OpenMRSTests {
    public static WebDriver driver;
    private TestNGCucumberRunner testRunner;

    @BeforeClass
    public void setUP() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        testRunner = new TestNGCucumberRunner(OpenMRSTests.class);

    }

    @Test(description = "login", dataProvider = "features")
    public void login(CucumberFeatureWrapper cFeature) {
        testRunner.runCucumber(cFeature.getCucumberFeature());
    }

    @DataProvider(name = "features")
    public Object[][] getFeatures() {
        return testRunner.provideFeatures();
    }

    @AfterClass
    public void tearDown() {
        testRunner.finish();
    }
}
