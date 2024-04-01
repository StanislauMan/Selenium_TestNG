import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.CustomerLoginPage;
import pages.LoginPage;

import java.time.Duration;

public class CustomerLoginTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void testCustomerLogin() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        Assert.assertEquals(customerLoginPage.getUserListLabel(), "Your Name :");
        customerLoginPage.selectCustomerUser("Hermoine Granger");
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        Assert.assertEquals(accountPage.getAccountWelcomeTitle(), "Hermoine Granger");
    }

    @AfterTest
    public void closer() {
        driver.quit();
    }
}
