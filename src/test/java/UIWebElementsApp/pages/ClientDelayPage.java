package UIWebElementsApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientDelayPage {
    protected WebDriver driver;

    private By btnTriggeringDelay = By.cssSelector("#ajaxButton");
    private By successBanner = By.cssSelector(".bg-success");

    public ClientDelayPage(WebDriver driver) {
        this.driver = driver;
    }

    public ClientDelayPage clickBtnTriggeringDelay() {
        driver.findElement(btnTriggeringDelay).click();
        return this;
    }

    public ClientDelayPage waitClientSideDelay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(successBanner));
        return this;
    }

    public String getSuccessBannerText() {
        return driver.findElement(successBanner).getText();
    }
}
