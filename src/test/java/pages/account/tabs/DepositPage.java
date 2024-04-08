package pages.account.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void typeDepositAmount(String amount) {
        driver.findElement(depositInputField).sendKeys(amount);
    }

    public boolean isDepositBtnEnabled() {
        return driver.findElement(depositBtn).isEnabled();
    }

    public void clickDepositBtn() {
        driver.findElement(depositBtn).click();
    }

    public String getDepositSuccessMessage() {
        return driver.findElement(depositSuccessMessage).getText();
    }
}
