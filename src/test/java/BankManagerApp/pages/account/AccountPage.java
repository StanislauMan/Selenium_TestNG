package BankManagerApp.pages.account;

import BankManagerApp.pages.account.tabs.DepositPage;
import BankManagerApp.pages.account.tabs.TransactionsPage;
import BankManagerApp.pages.account.tabs.WithdrawalPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountPage {
    protected WebDriver driver;

    private By accountWelcomeTitle = By.cssSelector("strong span");
    private By transactionsTab = By.cssSelector("button[ng-click='transactions()']");
    private By depositTab = By.cssSelector("button[ng-click='deposit()']");
    private By withdrawlTab = By.cssSelector("button[ng-click='withdrawl()']");
    private By accountList = By.cssSelector("#accountSelect");
    private By accountNumber = By.xpath("//div[contains(text(), 'Account Number :')]//strong[1]");
    private By balanceAmount = By.xpath("//div[contains(text(), 'Account Number :')]//strong[2]");
    private By currencyType = By.xpath("//div[contains(text(), 'Account Number :')]//strong[3]");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAccountWelcomeTitle() {
        return driver.findElement(accountWelcomeTitle).getText();
    }

    public TransactionsPage clickTransactionsTab() {
        driver.findElement(transactionsTab).click();
        return new TransactionsPage(driver);
    }

    public DepositPage clickDepositTab() {
        driver.findElement(depositTab).click();
        return new DepositPage(driver);
    }

    public WithdrawalPage clickWithdrawlTab() {
        driver.findElement(withdrawlTab).click();
        return new WithdrawalPage(driver);
    }

    public String getAccountNumber() {
        return driver.findElement(accountNumber).getText();
    }

    public int getBalanceAmount() {
        String balance = driver.findElement(balanceAmount).getText();
        return Integer.parseInt(balance);
    }

    public String getCurrencyType() {
        return driver.findElement(currencyType).getText();
    }

    public void selectAccountNumber(String number) {
        Select select = new Select(driver.findElement(this.accountList));
        select.selectByVisibleText(number);
    }
}
