package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragElementTest extends BaseTest {

//    @BeforeMethod
//    void beforeMethod(){
//        loginPage = new LoginPage();
//        productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
//                loginUsers.getJSONObject("validUser").getString("password"));
//    }

    @Test
    public void dragElementTest(){
        getDriver().findElement(AppiumBy.accessibilityId("Views")).click();
        getDriver().findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement element = getDriver().findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));

        dragElement(element);
    }
}
