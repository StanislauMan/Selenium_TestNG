package BankManagerApp.pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    protected WebDriver driver;

    private By homeBtn = By.cssSelector("button[ng-click='home()']");
    private By logoutBtn = By.cssSelector("button[ng-show='logout']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage clickHomeBtn() {
        driver.findElement(homeBtn).click();
        return new LoginPage(driver);
    }

    public CustomerLoginPage clickLogoutBtn() {
        driver.findElement(logoutBtn).click();
        return new CustomerLoginPage(driver);
    }

}
