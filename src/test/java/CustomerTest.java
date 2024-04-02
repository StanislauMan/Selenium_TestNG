import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.CustomerLoginPage;
import pages.Header;
import pages.LoginPage;
import pages.account.DepositPage;

import java.time.Duration;

public class CustomerTest {
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

    @Test
    public void testClickHomeButton() {
        Header header = new Header(driver);
        LoginPage loginPage = header.clickHomeBtn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    @Test
    public void testCustomerDeposit() {
        testCustomerLogin();
        AccountPage accountPage = new AccountPage(driver);
        String accountBalance = accountPage.getBalanceAmount();
        DepositPage depositPage = accountPage.clickDepositBtn();
        Assert.assertEquals(depositPage.getDepositFieldTitle(), "Amount to be Deposited :");
        String deposit = "1000";
        depositPage.typeDepositAmount(deposit);
        depositPage.clickDepositBtn();
        Assert.assertEquals(depositPage.getDepositSuccessMessage(), "Deposit Successful");
        Integer expectedBalance = Integer.parseInt(accountBalance) + Integer.parseInt(deposit);
        Assert.assertEquals(accountPage.getBalanceAmount(), Integer.toString(expectedBalance));

    }

    @AfterTest
    public void closer() {
        driver.quit();
    }
}
