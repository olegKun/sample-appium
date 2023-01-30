package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    BaseTest base;


    @iOSXCUITFindBy(accessibility = "test-Username")
    @AndroidFindBy(accessibility = "test-Username")
    private WebElement usernameField;
    @iOSXCUITFindBy(id = "test-Password")
    @AndroidFindBy(accessibility = "test-Password")
    private WebElement passwordField;
    @iOSXCUITFindBy(id = "test-LOGIN")
    @AndroidFindBy(accessibility = "test-LOGIN")
    private WebElement loginBtn;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Error message\"]/child::XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
    private WebElement errorText;

    public LoginPage() {
        base = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
    }

    public LoginPage enterUsername(String username) {
        base.clear(usernameField);
        base.sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        base.clear(passwordField);
        base.sendKeys(passwordField, password);
        return this;
    }

    public ProductsPage pressLoginBtn() {
        base.click(loginBtn);
        return new ProductsPage();
    }

    public String getErrorText() {
        return base.getText(errorText);
    }

    public ProductsPage login(String userName, String password) {
        enterUsername(userName);
        enterPassword(password);

        return pressLoginBtn();
    }
}
