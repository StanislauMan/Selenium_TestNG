package UIWebElementsApp.common;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

public class ListenersUIElements extends BaseClass implements ITestListener {
    public void onStart(ITestResult result) {
        //TODO
    }

    public void onTestStart(ITestResult result) {
        //TODO
    }

    public void onTestSuccess(ITestResult result) {
        //TODO
    }

    public void onTestFailure(ITestResult result) {
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    public void onTestSkipped(ITestResult result) {
        //TODO
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //TODO
    }

    public void onFinish(ITestContext context) {
        //TODO
    }
}
