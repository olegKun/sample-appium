package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;
import io.appium.java_client.remote.MobileCapabilityType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;

import static org.testng.Assert.assertEquals;

public class ProductTest extends BaseTest{
     private ProductDetailsPage productDetailsPage;


//    @BeforeClass
//    @Parameters({MobileCapabilityType.PLATFORM_NAME, MobileCapabilityType.DEVICE_NAME,
//            MobileCapabilityType.UDID
//    })
//    public void beforeClass(String platformName, String deviceName, @Optional String udid){
//        base = new BaseTest();
//        base.initialiseDriver(platformName, deviceName, udid);
//
//    }



    @BeforeMethod
    void beforeMethod(){
        loginPage = new LoginPage();
        productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password"));
    }

//    @Test
//    void validateProductOnProductsPage() {
//
//        String sauceLabsBackPackTitle = productsPage.getSauceLabsBackPackTitle();
//        String sauceLabsBackPackPrice = productsPage.getSauceLabsBackPackPrice();
//
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(sauceLabsBackPackTitle, "Sauce Labs Backpack");
//        softAssert.assertEquals(sauceLabsBackPackPrice, "$29.99");
//        softAssert.assertAll();
//    }

    @Test
    void validateProductOnProductDetailsPage() {

        productDetailsPage = productsPage.pressSLBTitle();

        String sauceLabsBackPackTitle = productDetailsPage.getSauceLabsBackPackTitle();
        String sauceLabsBackPackText = productDetailsPage.getSauceLabsBackPackText();

//        productDetailsPage.scrollToLBPrice();
//        String price = productDetailsPage.getSLBPrice();

//        productDetailsPage.scrollPage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sauceLabsBackPackTitle, "Sauce Labs Backpack");
        softAssert.assertEquals(sauceLabsBackPackText, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
//        softAssert.assertEquals(price, "$29.99");
        softAssert.assertAll();
    }
}
