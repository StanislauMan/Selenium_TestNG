package uiElements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class ShadowDomPage {
    protected WebDriver driver;

    private By shadowHost = By.cssSelector(".container guid-generator");
    private By shadowGenerateIdBtn = By.cssSelector("#buttonGenerate");
    private By shadowInputField = By.cssSelector("#editField");

    public ShadowDomPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchContext getShadowRootContext() {
        SearchContext shadowRoot = driver.findElement(shadowHost).getShadowRoot();
        return shadowRoot;
    }

    public void clickShadowGenerateIdBtn() {
        getShadowRootContext().findElement(shadowGenerateIdBtn).click();
    }

    public String getShadowInputFieldText() {
        String shadowFieldText = getShadowRootContext().findElement(shadowInputField).getAttribute("value");
        return shadowFieldText;
    }

    public void copyGeneratedId() {
        getShadowRootContext().findElement(shadowInputField).click();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
    }

    public String getClipboardText() throws IOException, UnsupportedFlavorException {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String clipboardCopiedText = (String) clipboard.getData(DataFlavor.stringFlavor);
        return clipboardCopiedText;
    }
}
