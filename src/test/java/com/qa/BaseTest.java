package com.qa;

import com.google.common.collect.ImmutableMap;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;
import com.qa.utils.TimeUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static com.qa.utils.WebDriverUtils.DEFAULT_WAIT_TIME;

public class BaseTest {
    protected static ThreadLocal<Properties> props = new ThreadLocal<>();
    protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    protected static ThreadLocal<String> platform = new ThreadLocal<>();
    protected static ThreadLocal<String> dateTime = new ThreadLocal<>();
    protected JSONObject loginUsers;
    protected ProductsPage productsPage;
    protected LoginPage loginPage;
    protected SettingsPage settingsPage;
    private TimeUtils timeUtils;

    Logger log = LogManager.getLogger(getClass());

    public BaseTest() {
        log.error("Init driver");
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public AppiumDriver getDriver() {
        return driver.get();
    }

    public void setDriver(AppiumDriver driver) {
        BaseTest.driver.set(driver);
    }

    public Properties getProps() {
        return props.get();
    }

    public void setProps(Properties props) {
        BaseTest.props.set(props);
    }

    public String getPlatform() {
        return platform.get();
    }

    public void setPlatform(String platform) {
        BaseTest.platform.set(platform);
    }

    public void setDateTime(String dateTime) {
        BaseTest.dateTime.set(dateTime);
    }

    public String getDateTime(String dateTime) {
        return BaseTest.dateTime.get();
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClassParent() {
        String dataFileName = "data/loginUsers.json";
        try (InputStream datais = getClass().getClassLoader().getResourceAsStream(dataFileName)) {
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @BeforeTest(alwaysRun = true)
    @Parameters({MobileCapabilityType.PLATFORM_NAME, MobileCapabilityType.DEVICE_NAME,
            MobileCapabilityType.UDID, "wdaLocalPort", "webkitDebugProxyPort", "systemPort",
            "emulator"
    })
    public void beforeTestParent(String platformName, String deviceName, @Optional String udid,
                                 @Optional() String wdaLocalPort,//ios only
                                 @Optional() String webkitDebugProxyPort,//ios only
                                 @Optional() String systemPort,//android only
                                 @Optional() String emulator) {
        log.info("Test info message");
        log.error("Test error message");
        Properties properties = new Properties();
        InputStream inputStream;
        try {
            setPlatform(platformName);
            String propFileName = "config.properties";
            setDateTime(TimeUtils.dateTimeFormat());
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            properties.load(inputStream);
            setProps(properties);

            URL url = new URL("http://localhost:4723/");
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);

            AppiumDriver driver;
            switch (platformName) {
                case "Android":
                    if (Boolean.parseBoolean(emulator)) {
                        caps.setCapability("systemPort", "Pixel_2");
//                        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
                    } else {
                        caps.setCapability(MobileCapabilityType.UDID, udid);
                    }
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("androidAutomationName"));
                    caps.setCapability("app", properties.getProperty("androidAppLocation"));

//                    caps.setCapability("appPackage", "com.swaglabsmobileapp");
//                    caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
//                    caps.setCapability("systemPort", systemPort);

                    driver = new AndroidDriver(url, caps);
                    break;
                case "iOS":
                    caps.setCapability(MobileCapabilityType.UDID, udid);
                    caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("iOSAutomationName"));
                    String appUrl = Objects.requireNonNull(getClass().getResource(properties.getProperty("iOSAppLocation"))).getFile();
                    caps.setCapability("app", appUrl);

                    caps.setCapability("wdaLocalPort", wdaLocalPort);
                    caps.setCapability("webkitDebugProxyPort", webkitDebugProxyPort);
                    driver = new IOSDriver(url, caps);
                    break;
                default:
                    throw new RuntimeException("Invalid platform " + platformName);
            }

            this.setDriver(driver);
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (IOException e) {
            throw new RuntimeException("An error occurred when initializing a driver", e);
        }

//        closeApp(platformName);
//        launchApp(platformName);

    }

    @BeforeMethod
    public void beforeMethod(Method method) {
//        ((CanRecordScreen) getDriver()).startRecordingScreen(
//                IOSStartScreenRecordingOptions.startScreenRecordingOptions()
//                        .withVideoType("mpeg4")
//        );

    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (productsPage != null) {
            settingsPage = productsPage.clickSettings();
            loginPage = settingsPage.logout();
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            String media = ((CanRecordScreen) getDriver()).stopRecordingScreen();
            Map<String, String> parameters = result.getTestContext().getCurrentXmlTest().getAllParameters();

            String dir = "videos" + File.separator + parameters.get("platformName") + "_" + parameters.get("platformVersion")
                    + "_" + parameters.get("deviceName") + File.separator + TimeUtils.dateTimeFormat() + File.separator +
                    result.getTestClass().getRealClass().getSimpleName();

            File videoDir = new File(dir);

            if (!videoDir.exists()) {
                videoDir.mkdirs();
            }

            try {
                String
                        destinationPath = dir + "/" + result.getName() + ".mp4";
                Path path = Paths.get(destinationPath);
                Files.write(path, Base64.decodeBase64(media));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(DEFAULT_WAIT_TIME));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement e, String attribute) {
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }

    public String getText(WebElement e) {
        switch (getPlatform()) {
            case "Android":
                return getAttribute(e, "text");
            case "iOS":
                return getAttribute(e, "label");
        }
        return null;
    }


    public void clear(WebElement e) {
        e.clear();
    }

    public void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    public void closeApp(String platformName) {
        switch (platformName) {
            case "Android":
                ((InteractsWithApps) getDriver()).terminateApp(getProps().getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) getDriver()).terminateApp(getProps().getProperty("iOSBundleId"));
                break;
        }
    }

    public void launchApp(String platformName) {
        switch (platformName) {
            case "Android":
                ((InteractsWithApps) getDriver()).activateApp(getProps().getProperty("androidAppPackage"));
                break;
            case "iOS":
                ((InteractsWithApps) getDriver()).activateApp(getProps().getProperty("iOSBundleId"));
                break;
        }
    }

    public WebElement scrollToElement() {
        return getDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" +
                ".scrollable(true)).scrollIntoView(new UiSelector().description(\"test-ADD TO CART\"));"));
    }

    public void scrollIosElement() {
        RemoteWebElement element = (RemoteWebElement) getDriver().findElement(By.name("test-ADD TO CART"));
        String elementId = element.getId();
        Map<String, String> scrollObject = new HashMap<>();
        scrollObject.put("element", elementId);
//        scrollObject.put("direction", "down");
//        scrollObject.put("predicateString", "label == \"ADD TO CART\" OR name == \"test-ADD TO CART\" or value == \"ADD TO CART\"");
//        scrollObject.put("name", "test-ADD TO CART");
        scrollObject.put("toVisible", "mhjkl");
        getDriver().executeScript("mobile:scroll", scrollObject);
    }

    public void dragElement(WebElement element) {

        getDriver().executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(),
                "endX", 100,
                "endY", 100
        ));
    }
}
