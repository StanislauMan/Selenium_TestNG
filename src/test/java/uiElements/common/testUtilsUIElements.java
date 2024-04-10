package uiElements.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class testUtilsUIElements extends BaseClass{

    public void getScreenshot() throws IOException {
        Date currentDate = new Date();
        String fileName = currentDate.toString().replace(" ", "").replace(":", "_");
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotFile, new File(".//screenshot//" + fileName + ".png"));
    }
}
