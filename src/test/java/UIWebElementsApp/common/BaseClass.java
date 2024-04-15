package UIWebElementsApp.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public static WebDriver driver;
    public static Properties properties;

    @BeforeTest
    public void setup() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/prorerties/local.uiElements.properties"));
        driver = new ChromeDriver();
    }

    public void driverNavigate(String url) {
        driver.get(url);
    }

    @AfterTest
    public void closer() {
        driver.quit();
    }
}
