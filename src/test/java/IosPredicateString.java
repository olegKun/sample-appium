import com.qa.CreateDriverSession;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.iOSBy;
import org.openqa.selenium.WebElement;

public class IosPredicateString {
    @AndroidBy()
    @iOSBy()
    WebElement element;

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("iOS");

        WebElement activityIndicators = driver.findElement(AppiumBy.iOSNsPredicateString("name == \"Activity Indicators\""));
        System.out.println(activityIndicators.getText());
    }
}
