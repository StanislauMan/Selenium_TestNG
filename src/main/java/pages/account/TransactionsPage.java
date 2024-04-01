package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;

public class TransactionsPage {
    protected WebDriver driver;

    private By backBtn = By.cssSelector("button[ng-click='back()']");
    private By resetBtn = By.cssSelector("button[ng-click='reset()']");
    private By startDatePicker = By.cssSelector("input[id='start']");
    private By endDatePicker = By.cssSelector("input[id='end']");
    private By scrollRightBtn = By.cssSelector("button[ng-click='scrollRight()']");
    private By scrollLeftBtn = By.cssSelector("button[ng-click='scrollLeft()']");
    private By scrollTopBtn = By.cssSelector("button[ng-click='scrollTop()']");

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public AccountPage clickBackBtn() {
        driver.findElement(backBtn).click();
        return new AccountPage(driver);
    }

}
