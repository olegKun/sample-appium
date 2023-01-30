package com.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class CreateDriverSession {
    Properties props = new Properties();
    protected AppiumDriver driver;
    static InputStream inputStream;

    public static AppiumDriver initializeDriver(String platformName) throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        switch (platformName) {
            case "Android":
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.UDID, "c5d2fd7f");

                caps.setCapability("appPackage", "com.swaglabsmobileapp");
                caps.setCapability("app",
                        "/Users/okunetskyi/Workspace/Ex_Files_Adv_Appium/sample-appium-project/src/test/" +
                                "resources/app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
//        desiredCapabilities.setCapability("avd", "Pixel_3_Android_11_oleg");
//        desiredCapabilities.setCapability("avdLaunchTimeout", 15000);
                return new AndroidDriver(url, caps);
            case "iOS":
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                caps.setCapability(MobileCapabilityType.UDID, "9BDC5347-EB2E-4DDE-97C8-E584F95744FA");
//                caps.setCapability("simulatorStartupTimeout", 180000);
                caps.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
//                caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
                return new IOSDriver(url, caps);
            default:
                throw new Exception("invalid platform");
        }
    }
}
