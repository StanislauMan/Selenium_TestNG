package pages.account.tabs;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.account.AccountPage;

public class TransactionsPage {
    protected WebDriver driver;

    private By backBtn = By.cssSelector("button[ng-click='back()']");
    private By resetBtn = By.cssSelector("button[ng-click='reset()']");
    private By transactionsTable = By.cssSelector("tbody");
    private By transactionAmount = By.xpath("//tbody/tr/td[2]");
    private By transactionType = By.xpath("//tbody/tr/td[3]");

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public AccountPage clickBackBtn() {
        driver.findElement(backBtn).click();
        return new AccountPage(driver);
    }

    public void clickResetBtn() {
        driver.findElement(resetBtn).click();
    }

    public String getTransactionAmount() {
        return driver.findElement(transactionAmount).getText();
    }

    public String getTransactionType() {
        return driver.findElement(transactionType).getText();
    }

    public boolean isResetBtnEnabled() {
        return driver.findElement(resetBtn).isEnabled();
    }

    public int getTransactionTableHeight() {
        Dimension tableSize =  driver.findElement(transactionsTable).getSize();
        return tableSize.getHeight();
    }

    public WebElement getTransactionTable() {
        return driver.findElement(transactionAmount);
    }

    public void checkTransactionsLoaded() {
        if(getTransactionTableHeight() == 0){
            driver.navigate().refresh();
        }
    }

}
