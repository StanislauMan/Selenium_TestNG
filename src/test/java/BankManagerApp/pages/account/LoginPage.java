package BankManagerApp.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    protected WebDriver driver;

    private By customerLoginBtn = By.cssSelector("button[ng-click='customer()']");
    private By bankManagerLoginBtn = By.cssSelector("button[ng-click='manager()']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public CustomerLoginPage clickCustomerLoginBtn() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(customerLoginBtn));
        driver.findElement(customerLoginBtn).click();
        return new CustomerLoginPage(driver);
    }

}
