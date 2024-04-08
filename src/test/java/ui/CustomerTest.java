package ui;

import common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.account.AccountPage;
import pages.account.CustomerLoginPage;
import pages.account.Header;
import pages.account.LoginPage;
import pages.account.tabs.DepositPage;
import pages.account.tabs.TransactionsPage;
import pages.account.tabs.WithdrawalPage;

public class CustomerTest extends BaseTest {


    @Test
    public void testCustomerLoginPageIsOpened() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();

        Assert.assertEquals(customerLoginPage.getUserListLabel(), properties.getProperty("userList_label"));
    }

    @Test
    public void testLoginBtnIsAppeared() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));

        Assert.assertTrue(customerLoginPage.isLoginBtnEnabled());
    }

    @Test
    public void testCustomerLogin() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();

        Assert.assertEquals(accountPage.getAccountWelcomeTitle(), properties.getProperty("username"));
    }

    @Test
    public void testClickHomeButton() {
        Header header = new Header(driver);
        LoginPage loginPage = header.clickHomeBtn();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
    }

    @Test
    public void testDepositTabIsOpened() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        DepositPage depositPage = accountPage.clickDepositBtn();

        Assert.assertEquals(depositPage.getDepositFieldTitle(), properties.getProperty("depositFieldTitle"));
    }

    @Test
    public void testDepositBtnEnabled() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        DepositPage depositPage = accountPage.clickDepositBtn();

        Assert.assertTrue(depositPage.isDepositBtnEnabled());
    }

    @Test
    public void testDepositSuccessMessageAppears() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        DepositPage depositPage = accountPage.clickDepositBtn();
        depositPage.typeDepositAmount(properties.getProperty("deposit"));
        depositPage.clickDepositBtn();

        Assert.assertEquals(depositPage.getDepositSuccessMessage(), properties.getProperty("depositSuccessMessage"));

    }

    @Test
    public void testCustomerDepositTransaction() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        int accountBalance = accountPage.getBalanceAmount();
        DepositPage depositPage = accountPage.clickDepositBtn();
        depositPage.typeDepositAmount(properties.getProperty("deposit"));
        depositPage.clickDepositBtn();
        int expectedBalance = accountBalance + Integer.parseInt(properties.getProperty("deposit"));

        Assert.assertEquals(accountPage.getBalanceAmount(), expectedBalance);

    }

    @Test
    public void testResetBtnIsEnabled() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        TransactionsPage transactionsPage = accountPage.clickTransactionsBtn();

        Assert.assertTrue(transactionsPage.isResetBtnEnabled());
    }

    @Test
    public void testClickResetBtn() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        TransactionsPage transactionsPage = accountPage.clickTransactionsBtn();
        transactionsPage.clickResetBtn();

        Assert.assertEquals(transactionsPage.getTransactionTableHeight(), 0);
    }

    @Test
    public void testCheckDepositTransaction() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        TransactionsPage transactionsPage = accountPage.clickTransactionsBtn();
        transactionsPage.clickResetBtn();
        transactionsPage.clickBackBtn();

        DepositPage depositPage = accountPage.clickDepositBtn();
        depositPage.typeDepositAmount(properties.getProperty("deposit"));
        depositPage.clickDepositBtn();

        Thread.sleep(1000);
        accountPage.clickTransactionsBtn();
        //transactionsPage.checkTransactionsLoaded();

//        Thread.sleep(1000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        wait.until(ExpectedConditions.visibilityOf(transactionsPage.getTransactionTable()));

        Assert.assertEquals(transactionsPage.getTransactionAmount(), properties.getProperty("deposit"));
        Assert.assertEquals(transactionsPage.getTransactionType(), properties.getProperty("transactionType"));
    }

    @Test
    public void testWithdrawlTabIsOpened() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        WithdrawalPage withdrawalPage = accountPage.clickWithdrawlBtn();

        Assert.assertEquals(withdrawalPage.getWithdrawalFieldTitle(), properties.getProperty("withdrawlFieldTitle"));
    }

    @Test
    public void testWithdrawlBtnIsEnabled() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        WithdrawalPage withdrawalPage = accountPage.clickWithdrawlBtn();

        Assert.assertTrue(withdrawalPage.isWithdrawalBtnEnabled());
    }

    @Test
    public void testErrorWithdrawlMessage() {
        LoginPage loginPage = new LoginPage(driver);
        CustomerLoginPage customerLoginPage = loginPage.clickCustomerLoginBtn();
        customerLoginPage.selectCustomerUser(properties.getProperty("username"));
        AccountPage accountPage = customerLoginPage.clickLoginBtn();
        if(accountPage.getBalanceAmount() != 0) {
            TransactionsPage transactionsPage = accountPage.clickTransactionsBtn();
            transactionsPage.clickResetBtn();
            transactionsPage.clickBackBtn();
        }
        WithdrawalPage withdrawalPage = accountPage.clickWithdrawlBtn();
        withdrawalPage.typeWithdrawalAmount(properties.getProperty("withdrawl"));
        withdrawalPage.clickWithdrawalBtn();

        Assert.assertEquals(withdrawalPage.getWithdrawalMessage(), properties.getProperty("withdrawlErrorMessage"));
    }
}
