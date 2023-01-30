package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends MenuPage {
    BaseTest base;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Toggle\"]/parent::*[1]/preceding-sibling::*[1]")
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/preceding-sibling::android.view.ViewGroup[2]//android.widget.TextView")
    WebElement productsText;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    WebElement sauceLabsBackPackTitle;

    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"test-Price\"])[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
    private WebElement sauceLabsBackPackPrice;

    public ProductsPage() {
        base = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
    }

    public String getTitle() {
        return getText(productsText);
    }

    public String getSauceLabsBackPackTitle() {
        return getText(sauceLabsBackPackTitle);
    }

    public String getSauceLabsBackPackPrice() {
        return getText(sauceLabsBackPackPrice);
    }

    public ProductDetailsPage pressSLBTitle() {
        click(sauceLabsBackPackTitle);
        return new ProductDetailsPage();
    }
}
