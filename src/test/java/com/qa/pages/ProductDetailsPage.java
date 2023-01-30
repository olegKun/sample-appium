package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class ProductDetailsPage extends MenuPage {
    BaseTest base;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Sauce Labs Backpack\"]")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    WebElement sauceLabsBackPackTitle;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Description\"]/XCUIElementTypeStaticText[2]")
    private WebElement sauceLabsBackPackText;

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    @iOSXCUITFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement backToProductsBtn;

    @iOSXCUITFindBy(accessibility = "test-Price")
    @AndroidFindBy(accessibility = "test-Price")
    private WebElement SLBPrice;

    @iOSXCUITFindBy(id = "test-ADD TO CART")
    @AndroidFindBy(accessibility = "test-ADD TO CART")
    private WebElement addToCartBtn;


    public ProductDetailsPage() {
        base = new BaseTest();

    }

    public String getSauceLabsBackPackTitle() {
        return getText(sauceLabsBackPackTitle);
    }

    public String getSauceLabsBackPackText() {
        return getText(sauceLabsBackPackText);
    }

    public ProductsPage pressBackToProductsBtn() {
        click(backToProductsBtn);
        return new ProductsPage();
    }

    public String getSLBPrice() {
        return getText(SLBPrice);
    }

    public ProductDetailsPage scrollToLBPrice() {
        scrollToElement();
        return this;
    }

    public void scrollPage() {
scrollIosElement();
    }
}
