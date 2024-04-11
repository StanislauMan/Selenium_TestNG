package UIWebElementsApp.tests;

import UIWebElementsApp.common.TestValues;
import UIWebElementsApp.pages.*;
import UIWebElementsApp.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import UIWebElementsApp.common.BaseClass;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class UIElementsTest extends BaseClass {

    @Test
    public void testDynamicID() {
        driverNavigate(properties.getProperty("/dynamicid"));

        new DynamicIDPage(driver).clickBtnWithDynamicID();
    }

    @Test
    public void testClientSideDelay() {
        driverNavigate(properties.getProperty("/clientdelay"));

        ClientDelayPage clientDelayPage = new ClientDelayPage(driver)
                .clickBtnTriggeringDelay()
                .waitClientSideDelay();

        Assert.assertEquals(clientDelayPage.getSuccessBannerText(), TestValues.CLIENT_DELAY_SUCCESS_MESSAGE);
    }

    @Test
    public void testShadowDDMClipboard() throws IOException, UnsupportedFlavorException {
        driverNavigate(properties.getProperty("/shadowdom"));

        ShadowDomPage shadowDomPage = new ShadowDomPage(driver)
                .clickShadowGenerateIdBtn()
                .copyGeneratedId();

        Assert.assertEquals(shadowDomPage.getShadowInputFieldText(), shadowDomPage.getClipboardText());
    }

    @Test
    public void testProgressBar() {
        driverNavigate(properties.getProperty("/progressbar"));

        ProgressBarPage progressBarPage = new ProgressBarPage(driver)
                .clickStartBtn()
                .waitProgressBarPercentage(TestValues.EXPECTED_PROGRESS_BAR_PERCENT)
                .clickStopBtn();

        Assert.assertEquals(progressBarPage.getProgressBarText(), TestValues.EXPECTED_PROGRESS_BAR_PERCENT);
    }

    @Test
    public void testDragAndDrop() {
        driverNavigate(properties.getProperty("/droppable"));

        DragAndDropPage dragAndDropPage = new DragAndDropPage(driver)
                .dragAndDropItem();

        Assert.assertEquals(dragAndDropPage.getDropBoxText(), TestValues.DROPPED_SUCCESS_MESSAGE);
    }

    @Test
    public void testIFrame() {
        driverNavigate(properties.getProperty("/iframe"));

        IFramePage iFramePage = new IFramePage(driver)
                .switchToIFrame()
                .waitIframeLoaded()
                .clearIFrameField()
                .typeToIFrameField(TestValues.TEXT);

        Assert.assertEquals(iFramePage.getIFrameFieldText(), TestValues.TEXT);
    }
}
