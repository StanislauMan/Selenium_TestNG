package UIWebElementsApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IFramePage {
    protected WebDriver driver;

    private String  iFrameId = "mce_0_ifr";
    private By iFrameInputField = By.cssSelector("#tinymce");
    private By iFrameFieldText = By.cssSelector("#tinymce p");

    public IFramePage(WebDriver driver) {
        this.driver = driver;
    }

    public IFramePage switchToIFrame() {
         driver.switchTo().frame(iFrameId);
         return this;
    }

    public IFramePage clearIFrameField() {
        driver.findElement(iFrameInputField).clear();
        return this;
    }

    public IFramePage waitIframeLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(iFrameInputField, "Your content goes here."));
        return this;
    }

    public IFramePage typeToIFrameField(String text) {
        driver.findElement(iFrameInputField).sendKeys(text);
        return this;
    }

    public String getIFrameFieldText() {
        return driver.findElement(iFrameFieldText).getText();
    }
}
