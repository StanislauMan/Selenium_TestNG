package pages.account.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WithdrawalPage {
    protected WebDriver driver;

    private By withdrawnFieldTitle = By.cssSelector(".form-group label");
    private By withdrawnInputField = By.cssSelector("input[ng-model='amount']");
    private By withdrawBtn = By.cssSelector("button[type='submit']");
    private By withdrawnMessage = By.cssSelector("span[ng-show='message']");

    public WithdrawalPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWithdrawalFieldTitle() {
        return driver.findElement(withdrawnFieldTitle).getText();
    }

    public boolean isWithdrawalBtnEnabled() {
        return driver.findElement(withdrawBtn).isEnabled();
    }

    public String getWithdrawalMessage() {
        return driver.findElement(withdrawnMessage).getText();
    }

    public void typeWithdrawalAmount(String amount) {
        driver.findElement(withdrawnInputField).sendKeys(amount);
    }

    public void clickWithdrawalBtn() {
        driver.findElement(withdrawBtn).click();
    }
}
