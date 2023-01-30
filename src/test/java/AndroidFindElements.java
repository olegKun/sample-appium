import com.qa.CreateDriverSession;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class AndroidFindElements {

    public static void main(String[] args) throws Exception {
        AppiumDriver appiumDriver = CreateDriverSession.initializeDriver("Android");


        WebElement elementByAccessibilityId = appiumDriver.findElement(AppiumBy.accessibilityId("Accessibility"));
//        System.out.println(elementByAccessibilityId.getText());
//        elementByAccessibilityId = appiumDriver.findElement(AppiumBy.id("android:id/text1"));
//        System.out.println(elementByAccessibilityId.getText());
//
//        elementByAccessibilityId = appiumDriver.findElement(AppiumBy.className("android.widget.TextView"));
//        System.out.println(elementByAccessibilityId.getText());
//
//        elementByAccessibilityId = appiumDriver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility\"]"));
//        System.out.println(elementByAccessibilityId.getText());
//
//        elementByAccessibilityId = appiumDriver.findElement(AppiumBy.name("Accessibility"));
//        System.out.println(elementByAccessibilityId.getText());
//
//        elementByAccessibilityId = appiumDriver.findElement(AppiumBy.tagName("Accessibility"));
//        System.out.println(elementByAccessibilityId.getText());


         elementByAccessibilityId = appiumDriver.findElement(new AppiumBy.ByAndroidUIAutomator("new UiSelector().text(\"Accessibility\")"));

        System.out.println(elementByAccessibilityId.getText());

         elementByAccessibilityId.click();

    }
}
