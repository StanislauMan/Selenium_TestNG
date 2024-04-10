package uiElements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IFramePage {
    protected WebDriver driver;

    private String  iFrameId = "mce_0_ifr";
    private By iFrameInputField = By.cssSelector("#tinymce");
    private By iFrameFieldText = By.cssSelector("#tinymce p");

    public IFramePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver switchToIFrame() {
        return driver.switchTo().frame(iFrameId);
    }

    public void clearIFrameField() {
        driver.findElement(iFrameInputField).clear();
    }

    public void typeToIFrameField(String text) {
        driver.findElement(iFrameInputField).sendKeys(text);
    }

    public String getIFrameFieldText() {
        return driver.findElement(iFrameFieldText).getText();
    }
}
