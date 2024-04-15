package UIWebElementsApp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicIDPage {
    protected WebDriver driver;

    private By btnWithDynamicID = By.cssSelector(".btn-primary");

    public DynamicIDPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBtnWithDynamicID() {
        driver.findElement(btnWithDynamicID).click();
    }

}
