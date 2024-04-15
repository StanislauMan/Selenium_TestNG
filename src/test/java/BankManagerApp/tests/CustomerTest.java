package BankManagerApp.tests;

import BankManagerApp.common.BaseTest;
import BankManagerApp.common.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import BankManagerApp.pages.account.AccountPage;
import BankManagerApp.pages.account.CustomerLoginPage;
import BankManagerApp.pages.account.Header;
import BankManagerApp.pages.account.LoginPage;
import BankManagerApp.pages.account.tabs.DepositPage;
import BankManagerApp.pages.account.tabs.TransactionsPage;
import BankManagerApp.pages.account.tabs.WithdrawalPage;

public class CustomerTest extends BaseTest {


    @Test
    public void testCustomerLoginPageIsOpened() {
        CustomerLoginPage customerLoginPage = new LoginPage(driver)
                .clickCustomerLoginBtn();

        Assert.assertEquals(customerLoginPage.getUserListLabel(), TestData.USER_LIST_LABEL);
    }

    @Test
    public void testLoginBtnIsAppeared() {
        CustomerLoginPage customerLoginPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME);

        Assert.assertTrue(customerLoginPage.isLoginBtnEnabled());
    }

    @Test
    public void testCustomerLogin() {
        AccountPage accountPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn();

        Assert.assertEquals(accountPage.getAccountWelcomeTitle(), TestData.USERNAME);
    }

    @Test
    public void testClickHomeButton() {
        new Header(driver).clickHomeBtn();

        Assert.assertEquals(driver.getCurrentUrl(), properties.getProperty("base_url"));
    }

    @Test
    public void testDepositTabIsOpened() {
        DepositPage depositPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickDepositTab();

        Assert.assertEquals(depositPage.getDepositFieldTitle(), TestData.DEPOSIT_FIELD_TITLE);
    }

    @Test
    public void testDepositBtnEnabled() {
        DepositPage depositPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickDepositTab();

        Assert.assertTrue(depositPage.isDepositBtnEnabled());
    }

    @Test
    public void testDepositSuccessMessageAppears() {
        DepositPage depositPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickDepositTab()
                .typeDepositAmount(TestData.DEPOSIT)
                .clickDepositBtn();

        Assert.assertEquals(depositPage.getDepositSuccessMessage(), TestData.DEPOSIT_SUCCESS_MESSAGE);

    }

    @Test
    public void testCustomerDepositTransaction() {
        AccountPage accountPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn();
        int accountBalance = accountPage.getBalanceAmount();
        accountPage.clickDepositTab()
                .typeDepositAmount(TestData.DEPOSIT)
                .clickDepositBtn();
        int expectedBalance = accountBalance + Integer.parseInt(TestData.DEPOSIT);

        Assert.assertEquals(accountPage.getBalanceAmount(), expectedBalance);

    }

    @Test
    public void testResetBtnIsEnabled() {
        TransactionsPage transactionsPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickTransactionsTab();

        Assert.assertTrue(transactionsPage.isResetBtnEnabled());
    }

    @Test
    public void testClickResetBtn() {
        TransactionsPage transactionsPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickTransactionsTab()
                .clickResetBtn();

        Assert.assertEquals(transactionsPage.getTransactionTableHeight(), 0);
    }

    @Test
    public void testCheckDepositTransaction() {
        new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickTransactionsTab()
                .clickResetBtn()
                .clickBackBtn()
                .clickDepositTab()
                .typeDepositAmount(TestData.DEPOSIT)
                .clickDepositBtn();
        TransactionsPage transactionsPage = new AccountPage(driver)
                .clickTransactionsTab()
                .checkTransactionsLoaded();

        Assert.assertEquals(transactionsPage.getTransactionAmount(), TestData.DEPOSIT);
        Assert.assertEquals(transactionsPage.getTransactionType(), TestData.TRANSACTION_TYPE);
    }

    @Test
    public void testWithdrawlTabIsOpened() {
        WithdrawalPage withdrawalPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickWithdrawlTab();

        Assert.assertEquals(withdrawalPage.getWithdrawalFieldTitle(), TestData.WITHDRAWL_FIELD_TITLE);
    }

    @Test
    public void testWithdrawlBtnIsEnabled() {
        WithdrawalPage withdrawalPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickWithdrawlTab();

        Assert.assertTrue(withdrawalPage.isWithdrawalBtnEnabled());
    }

    @Test
    public void testErrorWithdrawlMessage() {
        WithdrawalPage withdrawalPage = new LoginPage(driver)
                .clickCustomerLoginBtn()
                .selectCustomerUser(TestData.USERNAME)
                .clickLoginBtn()
                .clickWithdrawlTab();
        String negativeWithdrawal = String.valueOf(new AccountPage(driver).getBalanceAmount()) + TestData.WITHDRAWL;
        withdrawalPage.typeWithdrawalAmount(negativeWithdrawal)
                .clickWithdrawalBtn();

        Assert.assertEquals(withdrawalPage.getWithdrawalMessage(), TestData.WITHDRAWL_ERROR_MESSAGE);
    }
}
