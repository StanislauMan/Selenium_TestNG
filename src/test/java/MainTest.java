import dev.failsafe.internal.util.Durations;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

public class MainTest {

    @Test
    public void testDynamicID() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestingplayground.com/dynamicid");

        driver.manage().timeouts().getPageLoadTimeout();

        WebElement buttonWithDynamicID = driver.findElement(By.cssSelector(".btn-primary"));
        buttonWithDynamicID.click();

        driver.quit();
    }

    @Test
    public void testClientSideDelay() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestingplayground.com/clientdelay");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        WebElement buttonTriggeringDelay = driver.findElement(By.cssSelector("#ajaxButton"));
        //WebElement successBanner = driver.findElement(By.cssSelector(".bg-success"));

        buttonTriggeringDelay.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".bg-success")));

        WebElement successBanner = driver.findElement(By.cssSelector(".bg-success"));

        String bannerText = successBanner.getText();
        Assert.assertEquals(bannerText, "Data calculated on the client side.");

        driver.quit();
    }

    @Test
    public void testShadowDDMClipboard() throws IOException, UnsupportedFlavorException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestingplayground.com/shadowdom");

        WebElement shadowHost = driver.findElement(By.cssSelector(".container guid-generator"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        shadowRoot.findElement(By.cssSelector("#buttonGenerate")).click();
        WebElement shadowField = shadowRoot.findElement(By.cssSelector("#editField"));

        String shadowFieldText = shadowField.getAttribute("value");

        shadowField.click();

        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));

        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

        //shadowRoot.findElement(By.cssSelector("#buttonCopy")).click();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String clipboardCopiedText = (String) clipboard.getData(DataFlavor.stringFlavor);

        Assert.assertEquals(shadowFieldText, clipboardCopiedText);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        driver.quit();
    }

    @Test
    public void testProgressBar() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://uitestingplayground.com/progressbar");

        WebElement startBtn = driver.findElement(By.cssSelector("#startButton"));
        WebElement stopBtn = driver.findElement(By.cssSelector("#stopButton"));
        WebElement progressBar = driver.findElement(By.cssSelector("#progressBar"));

        startBtn.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.textToBePresentInElement(progressBar, "75%"));

        //wait.until((WebDriver webDriver) -> waitForProgressBar(progressBar, "75%"));

        stopBtn.click();

        Assert.assertEquals(progressBar.getText(), "75%");

        driver.quit();

    }

    private static boolean waitForProgressBar(WebElement progressBar, String targetText) {
        String progressBarText = progressBar.getText();
        return progressBarText.contains(targetText);
    }

    @Test
    public void testDragAndDropSimple() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/droppable");

        WebElement dragEl = driver.findElement(By.cssSelector("#draggable"));
        WebElement dropEl = driver.findElement(By.cssSelector(".simple-drop-container #droppable"));

        Actions action = new Actions(driver);
        action.dragAndDrop(dragEl, dropEl).perform();

        String droppedText = driver.findElement(By.cssSelector(".simple-drop-container #droppable p")).getText();

        Assert.assertEquals(droppedText, "Dropped!");

        driver.quit();
    }

    @Test
    public void testDragAndDropAccept() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/droppable");

        WebElement acceptTab = driver.findElement(By.cssSelector("#droppableExample-tab-accept"));
        acceptTab.click();

        WebElement droppedText = driver.findElement(By.cssSelector(".accept-drop-container #droppable p"));

        Assert.assertEquals(droppedText.getText(), "Drop here");

        WebElement acceptableEl = driver.findElement(By.cssSelector("#acceptable"));
        WebElement notAcceptableEl = driver.findElement(By.cssSelector("#notAcceptable"));
        WebElement dropEl = driver.findElement(By.cssSelector(".accept-drop-container #droppable"));

        Actions action = new Actions(driver);
        action.dragAndDrop(notAcceptableEl, dropEl).perform();

        Assert.assertEquals(droppedText.getText(), "Drop here");

        action.dragAndDrop(acceptableEl, dropEl).perform();

        Assert.assertEquals(droppedText.getText(), "Dropped!");

        driver.quit();
    }

    @Test
    public void testIFrame() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/iframe");

        driver.switchTo().frame("mce_0_ifr");

        WebElement iFramefield = driver.findElement(By.cssSelector("#tinymce"));

        Thread.sleep(1000);
        iFramefield.clear();

        iFramefield.sendKeys("Test Data - !");

        WebElement iFrameInputText = driver.findElement(By.cssSelector("#tinymce p"));

        Assert.assertEquals(iFrameInputText.getText(), "Test Data - !");

        driver.quit();
    }


}
