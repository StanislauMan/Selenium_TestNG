package BankManagerApp.pages.account.tabs;

import BankManagerApp.pages.account.AccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DepositPage {
    protected WebDriver driver;

    private By depositFieldTitle = By.cssSelector(".form-group label");
    private By depositInputField = By.cssSelector("input[ng-model='amount']");
    private By depositBtn = By.cssSelector("button[type='submit']");
    private By depositSuccessMessage = By.cssSelector("span[ng-show='message']");

    public DepositPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getDepositFieldTitle() {
        return driver.findElement(depositFieldTitle).getText();
    }

    public DepositPage typeDepositAmount(String amount) {
        driver.findElement(depositInputField).sendKeys(amount);
        return this;
    }

    public boolean isDepositBtnEnabled() {
        return driver.findElement(depositBtn).isEnabled();
    }

    public DepositPage clickDepositBtn() {
        driver.findElement(depositBtn).click();
        return this;
    }

    public String getDepositSuccessMessage() {
        return driver.findElement(depositSuccessMessage).getText();
    }
}
