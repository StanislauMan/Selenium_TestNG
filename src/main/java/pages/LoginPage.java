package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    protected WebDriver driver;

    private By customerLoginBtn = By.cssSelector("button[ng-click='customer()']");
    private By bankManagerLoginBtn = By.cssSelector("button[ng-click='manager()']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public CustomerLoginPage clickCustomerLoginBtn() {
        driver.findElement(customerLoginBtn).click();
        return new CustomerLoginPage(driver);
    }

}
