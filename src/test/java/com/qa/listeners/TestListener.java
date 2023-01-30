package com.qa.listeners;

import com.qa.BaseTest;
import com.qa.utils.TimeUtils;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        if (iTestResult.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            iTestResult.getThrowable().printStackTrace(pw);
            iTestResult.getThrowable().printStackTrace(System.out);
        }

        BaseTest base = new BaseTest();
        AppiumDriver driver = base.getDriver();
        File screenshot = driver.getScreenshotAs(OutputType.FILE);
        try {
            Map<String, String> parameters = iTestResult.getTestContext().getCurrentXmlTest().getAllParameters();
            String imagePath = "Screenshots" + File.separator + parameters.get("platformName")+"_"+parameters.get("platformVersion")
                    +"_"+parameters.get("deviceName") +File.separator+ TimeUtils.dateTimeFormat()+File.separator+
                    iTestResult.getTestClass().getRealClass().getSimpleName()+File.separator+iTestResult.getName()+".png";
            File file = new File(imagePath);
            FileUtils.copyFile(screenshot, file);
            String absoluteImagePath = file.getAbsolutePath();
            Reporter.log("This is a sample screenshot");
            Reporter.log("<a href='" + absoluteImagePath+"'> <img src='"+ absoluteImagePath +"' height='100' width='100'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
