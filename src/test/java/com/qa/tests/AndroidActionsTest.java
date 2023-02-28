package com.qa.tests;

import com.google.common.collect.ImmutableMap;
import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndroidActionsTest extends BaseTest {

//    @BeforeMethod
//    void beforeMethod(){
//        loginPage = new LoginPage();
//        productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
//                loginUsers.getJSONObject("validUser").getString("password"));
//    }

//    @Test
//    public void dragElementTest(){
//        getDriver().findElement(AppiumBy.accessibilityId("Views")).click();
//        getDriver().findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
//        WebElement element = getDriver().findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
//
//        dragElement(element);
//    }

    @Test
    public void testScrolling() {
        driver.get().findElement(AppiumBy.accessibilityId("Views")).click();

        WebElement element = driver.get().findElement(AppiumBy.id("android:id/list"));

        ((JavascriptExecutor) driver.get()).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 1000, "height", 1000,
//                "elementId", ((RemoteWebElement) element).getId(),
                "direction", "up",
                "percent", 0.75
        ));

    }
}
