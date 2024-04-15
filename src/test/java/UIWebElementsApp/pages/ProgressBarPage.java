package UIWebElementsApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage {
    protected WebDriver driver;

    private By startBtn = By.cssSelector("#startButton");
    private By stopBtn = By.cssSelector("#stopButton");
    private By progressBar = By.cssSelector("#progressBar");

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProgressBarPage clickStartBtn() {
        driver.findElement(startBtn).click();
        return this;
    }

    public ProgressBarPage waitProgressBarPercentage(String percentage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(progressBar), percentage));
        return this;
    }

    public ProgressBarPage clickStopBtn() {
        driver.findElement(stopBtn).click();
        return this;
    }

    public String getProgressBarText() {
        return driver.findElement(progressBar).getText();
    }
}
