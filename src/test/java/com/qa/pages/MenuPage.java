package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"test-Menu\"`]/XCUIElementTypeOther")
    private WebElement settingsBtn;

    public SettingsPage clickSettings(){
        click(settingsBtn);
        return new SettingsPage();
    }
}
