package uiElements.common;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class ListenersUIElements extends testUtilsUIElements implements ITestListener {
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
