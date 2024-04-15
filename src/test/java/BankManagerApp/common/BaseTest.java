package BankManagerApp.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static Properties properties;

    @BeforeMethod
    public void setup() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/test/resources/prorerties/local.properties"));
        driver = new ChromeDriver();
        driver.get(properties.getProperty("base_url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterMethod
    public void closer() {
        driver.quit();
    }
}
