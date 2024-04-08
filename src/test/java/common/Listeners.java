package common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.testUtils;

import java.io.IOException;

public class Listeners extends testUtils implements ITestListener {
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
        try {
            getScreenshot();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
