package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest{
//    LoginPage loginPage;
     ProductsPage productsPage;
//     JSONObject loginUsers ;

    @BeforeClass
    @Parameters({MobileCapabilityType.PLATFORM_NAME, MobileCapabilityType.DEVICE_NAME,
            MobileCapabilityType.UDID
    })
    void beforeClass(String platformName, String deviceName, @Optional String udid){

    }

    @AfterTest(alwaysRun = true)
    public void afterClass(){
        quitDriver();
    }

    @BeforeMethod
    public void beforeMethod(){
        loginPage = new LoginPage();
    }

    @AfterMethod
    void afterMethod(){

    }

    @Test
    void testInvalidUsername() {
        loginPage.enterUsername(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrorMessage = loginPage.getErrorText();
        String expectedErrorMessage = "Username and password do not match any user in this service.";

        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    void testInvalidPassword() {
        loginPage.enterUsername(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrorMessage = loginPage.getErrorText();
        String expectedErrorMessage = "Username and password do not match any user in this service.";

        assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    void testValidCredentials() {
        loginPage.enterUsername(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        loginPage.pressLoginBtn();

        System.out.println();


//        WebElement productsText = driver.findElement(AppiumBy.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup[2]//android.widget.TextView"));
//        assertEquals(productsText.getText(), "PRODUCTS");
    }
}
