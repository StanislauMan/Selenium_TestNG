package ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import uiElements.common.BaseClass;
import uiElements.pages.*;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class UIElementsTest extends BaseClass {

    @Test
    public void testDynamicID() {
        driver.get(properties.getProperty("/dynamicid"));

        DynamicIDPage dynamicIDPage = new DynamicIDPage(driver);
        dynamicIDPage.clickBtnWithDynamicID();
    }

    @Test
    public void testClientSideDelay() {
        driver.get(properties.getProperty("/clientdelay"));

        ClientDelayPage clientDelayPage = new ClientDelayPage(driver);
        clientDelayPage.clickBtnTriggeringDelay();
        clientDelayPage.waitClientSideDelay();

        Assert.assertEquals(clientDelayPage.getSuccessBannerText(), properties.getProperty("clientDelaySuccessMessage"));
    }

    @Test
    public void testShadowDDMClipboard() throws IOException, UnsupportedFlavorException {
        driver.get(properties.getProperty("/shadowdom"));

        ShadowDomPage shadowDomPage = new ShadowDomPage(driver);
        shadowDomPage.clickShadowGenerateIdBtn();
        shadowDomPage.copyGeneratedId();

        Assert.assertEquals(shadowDomPage.getShadowInputFieldText(), shadowDomPage.getClipboardText());
    }

    @Test
    public void testProgressBar() {
        driver.get(properties.getProperty("/progressbar"));

        ProgressBarPage progressBarPage = new ProgressBarPage(driver);
        progressBarPage.clickStartBtn();
        progressBarPage.waitProgressBarPercentage(properties.getProperty("expectedProgressBarPercent"));
        progressBarPage.clickStopBtn();

        Assert.assertEquals(progressBarPage.getProgressBarText(), properties.getProperty("expectedProgressBarPercent"));
    }

    @Test
    public void testDragAndDrop() {
        driver.get(properties.getProperty("/droppable"));

        DragAndDropPage dragAndDropPage = new DragAndDropPage(driver);
        dragAndDropPage.dragAndDropItem();

        Assert.assertEquals(dragAndDropPage.getDropBoxText(), properties.getProperty("droppedSuccessMessage"));
    }

    @Test
    public void testIFrame() {
        driver.get(properties.getProperty("/iframe"));

        IFramePage iFramePage = new IFramePage(driver);
        iFramePage.switchToIFrame();
        iFramePage.clearIFrameField();
        iFramePage.typeToIFrameField(properties.getProperty("text"));

        Assert.assertEquals(iFramePage.getIFrameFieldText(), properties.getProperty("text"));
    }


}
