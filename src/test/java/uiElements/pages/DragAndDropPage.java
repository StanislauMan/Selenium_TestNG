package uiElements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage {
    protected WebDriver driver;

    private By dragItem = By.cssSelector("#draggable");
    private By dropBox = By.cssSelector(".simple-drop-container #droppable");
    private By dropBoxText = By.cssSelector(".simple-drop-container #droppable p");

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
    }

    public void dragAndDropItem() {
        Actions actions = new Actions(driver);
        actions.dragAndDrop(driver.findElement(dragItem), driver.findElement(dropBox)).perform();
    }

    public String getDropBoxText() {
        return driver.findElement(dropBoxText).getText();
    }



}
