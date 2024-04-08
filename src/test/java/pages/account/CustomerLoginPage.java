package pages.account;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CustomerLoginPage {
    protected WebDriver driver;

    private By userDropDownList = By.cssSelector(("#userSelect"));
    private By loginBtn = By.cssSelector(("button[type='submit']"));
    private By userListLabel = By.cssSelector(".form-group label");

    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserListLabel() {
        return driver.findElement(userListLabel).getText();
    }

    public void selectCustomerUser(String username) {
        Select select = new Select(driver.findElement(this.userDropDownList));
        select.selectByVisibleText(username);
    }

    public boolean isLoginBtnEnabled() {
        return driver.findElement(loginBtn).isEnabled();
    }

    public AccountPage clickLoginBtn() {
        driver.findElement(loginBtn).click();
        return new AccountPage(driver);
    }


}
