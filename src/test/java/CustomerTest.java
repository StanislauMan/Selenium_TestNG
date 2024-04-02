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
import pages.account.TransactionsPage;
import utility.Constant;

import java.time.Duration;

public class CustomerTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get(Constant.URL);
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
        String deposit = "1000";

        testCustomerLogin();
        AccountPage accountPage = new AccountPage(driver);
        int accountBalance = accountPage.getBalanceAmount();
        DepositPage depositPage = accountPage.clickDepositBtn();
        Assert.assertEquals(depositPage.getDepositFieldTitle(), "Amount to be Deposited :");
        depositPage.typeDepositAmount(deposit);
        depositPage.clickDepositBtn();
        Assert.assertEquals(depositPage.getDepositSuccessMessage(), "Deposit Successful");
        int expectedBalance = accountBalance + Integer.parseInt(deposit);
        Assert.assertEquals(accountPage.getBalanceAmount(), Integer.toString(expectedBalance));

    }

    @Test
    public void testResetTransactionData() {
        testCustomerLogin();
        AccountPage accountPage = new AccountPage(driver);
        int accountBalance = accountPage.getBalanceAmount();
        TransactionsPage transactionsPage = accountPage.clickTransactionsBtn();
        transactionsPage.clickResetBtn();
        transactionsPage.clickBackBtn();
    }

    @Test
    public void testCheckDepositTransaction() throws InterruptedException {
        testResetTransactionData();
        String deposit = "1000";

        AccountPage accountPage = new AccountPage(driver);
        int accountBalance = accountPage.getBalanceAmount();
        DepositPage depositPage = accountPage.clickDepositBtn();
        Assert.assertEquals(depositPage.getDepositFieldTitle(), "Amount to be Deposited :");
        depositPage.typeDepositAmount(deposit);
        depositPage.clickDepositBtn();
        Assert.assertEquals(depositPage.getDepositSuccessMessage(), "Deposit Successful");
        int expectedBalance = accountBalance + Integer.parseInt(deposit);
        Assert.assertEquals(accountPage.getBalanceAmount(), expectedBalance);

        Thread.sleep(1000);
        TransactionsPage transactionsPage = accountPage.clickTransactionsBtn();
        Assert.assertEquals(transactionsPage.getTransactionAmount(), deposit);
        Assert.assertEquals(transactionsPage.getTransactionType(), "Credit");

    }

    @AfterTest
    public void closer() {
        driver.quit();
    }
}
