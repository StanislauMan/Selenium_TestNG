package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DepositPage {
    protected WebDriver driver;

    private By depositFieldTitle = By.cssSelector(".form-group label");
    private By depositAmountField = By.cssSelector("input[ng-model='amount']");
    private By depositBtn = By.cssSelector("button[type='submit']");
    private By depositSuccessMessage = By.cssSelector("span[ng-show='message']");

    public DepositPage(WebDriver driver) {
        this.driver = driver;
    }
}
