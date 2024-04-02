package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.account.DepositPage;
import pages.account.TransactionsPage;
import pages.account.WithdrawlPage;

public class AccountPage {
    protected WebDriver driver;

    private By accountWelcomeTitle = By.cssSelector("strong span");
    private By transactionsBtn = By.cssSelector("button[ng-click='transactions()']");
    private By depositBtn = By.cssSelector("button[ng-click='deposit()']");
    private By withdrawlBtn = By.cssSelector("button[ng-click='withdrawl()']");
    private By accountList = By.cssSelector("#accountSelect");
    private By accountNumber = By.xpath("//body//div//div//div//div//div[2]/strong[1]");
    private By balanceAmount = By.xpath("//body//div//div//div//div//div[2]/strong[2]");
    private By currencyType = By.xpath("//body//div//div//div//div//div[2]/strong[3]");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAccountWelcomeTitle() {
        return driver.findElement(accountWelcomeTitle).getText();
    }

    public TransactionsPage clickTransactionsBtn() {
        driver.findElement(transactionsBtn).click();
        return new TransactionsPage(driver);
    }

    public DepositPage clickDepositBtn() {
        driver.findElement(depositBtn).click();
        return new DepositPage(driver);
    }

    public WithdrawlPage clickWithdrawlBtn() {
        driver.findElement(withdrawlBtn).click();
        return new WithdrawlPage(driver);
    }

    public String getAccountNumber() {
        return driver.findElement(accountNumber).getText();
    }

    public String getBalanceAmount() {
        return driver.findElement(balanceAmount).getText();
    }

    public String getCurrencyType() {
        return driver.findElement(currencyType).getText();
    }

    public void selectAccountNumber(String number) {
        Select select = new Select(driver.findElement(this.accountList));
        select.selectByVisibleText(number);
    }
}
