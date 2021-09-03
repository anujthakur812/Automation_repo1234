package DemoMidTrans;

import Pages.*;
import Utilities.Setup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCases extends Setup {

    public WebDriver driver;
    BasePage basePage;
    CheckoutPage checkoutPage;
    OrderSummaryPage orderSummaryPage;
    SelectPaymentPage selectPaymentPage;
    CreditDebitCardPage creditDebitCardPage;
    BankPaymentPage bankPaymentPage;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser() {
        driver = launchBrowser("chrome");
//        driver = launchBrowser("firefox");
        basePage = new BasePage(driver);
        checkoutPage = new CheckoutPage(driver);
        orderSummaryPage = new OrderSummaryPage(driver);
        selectPaymentPage = new SelectPaymentPage(driver);
        creditDebitCardPage = new CreditDebitCardPage(driver);
        bankPaymentPage = new BankPaymentPage(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void tearUp() {
        driver.get(basePage.properties.getProperty("url"));
    }

    @Test(priority = 1, groups = {"regression"})
    public void verifyShoppingCartDetailsArePresent() {
        basePage.clickOnBuyNowButton();
        Assert.assertTrue(checkoutPage.shoppingCartDetailsAreCorrect());
    }

    @Test(priority = 2, groups = {"smoke", "regression"})
    public void verifyClickingOnBuyNowUserGetsRedirectedToCheckoutPopUp() {
        basePage.clickOnBuyNowButton();
        Assert.assertTrue(checkoutPage.checkoutPopUpIsDisplayed());
    }

    @Test(priority = 3, groups = {"regression"})
    public void verifyCustomerDetailsArePresent() {
        basePage.clickOnBuyNowButton();
        Assert.assertTrue(checkoutPage.CustomerDetailsAreCorrect());
    }

    @Test(priority = 4, groups = {"regression"})
    public void verifyUserCanEnterCustomerDetails() {
        basePage.clickOnBuyNowButton();
        checkoutPage.enterCustomerDetails();
    }

    @Test(priority = 5, groups = {"smoke", "regression"})
    public void verifyUserRedirectsToOrderSummaryPopUpOnClickingCheckoutButton() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        Assert.assertTrue(orderSummaryPage.orderSummaryPopUpIsVisible());
    }

    @Test(priority = 6, groups = {"regression"})
    public void verifyProductOrderDetailsOnOrderSummaryPopUpPage() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        Assert.assertTrue(orderSummaryPage.orderDetailsArePresent());
    }

    @Test(priority = 7, groups = {"smoke", "regression"})
    public void verifyUserGetsRedirectedToSelectPaymentPageOnClickingContinueInOrderSummaryPage() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        Assert.assertTrue(selectPaymentPage.selectPaymentPageIsDisplayed());
    }

    @Test(priority = 8, groups = {"regression"})
    public void verifyAllThePaymentOptionsAreListedOnSelectPaymentPage() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        Assert.assertTrue(selectPaymentPage.paymentOptionsAreDisplayed());
    }

    @Test(priority = 9, groups = {"smoke", "regression"})
    public void verifyUserGetsRedirectedToCreditDebitCardDetailsScreen() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        Assert.assertTrue(creditDebitCardPage.CreditDebitCardDetailsDisplayed());
    }

    @Test(priority = 10, groups = {"regression"})
    public void verifyClickingOnCouponCodesAndValidatingOrderAmount() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        Assert.assertTrue(creditDebitCardPage.clickOnFirstCouponCodeAndValidateOrderAmount());
        Assert.assertTrue(creditDebitCardPage.clickOnSecondCouponCodeAndValidateOrderAmount());
        Assert.assertTrue(creditDebitCardPage.clickOnThirdCouponCodeAndValidateOrderAmount());
    }

    @Test(priority = 11, groups = {"smoke", "regression"})
    public void verifyEnteringValidCardDetailsAndClickingOnPayNow() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        creditDebitCardPage.enterCardDetails();
        creditDebitCardPage.clickOnPayNowButton();
    }

    @Test(priority = 12, groups = {"regression"})
    public void verifyDetailsOnBankPaymentScreen() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        creditDebitCardPage.enterCardDetails();
        creditDebitCardPage.clickOnPayNowButton();
//        verifyEnteringValidCardDetailsAndClickingOnPayNow();
        bankPaymentPage.switchToBankPaymentFrame();
        Assert.assertTrue(bankPaymentPage.detailsAreDisplayedOnBankPaymentScreen());
    }

    @Test(priority = 13, groups = {"smoke", "regression"})
    public void verifyOnClickingOnOkButtonUserGetsRedirectedToOrderSuccessfulScreen() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        creditDebitCardPage.enterCardDetails();
        creditDebitCardPage.clickOnPayNowButton();
//        verifyEnteringValidCardDetailsAndClickingOnPayNow();
        bankPaymentPage.switchToBankPaymentFrame();
        bankPaymentPage.enterPassword(basePage.properties.getProperty("password"));
        bankPaymentPage.clickOnOkButton();
        basePage.switchToDefaultContent();
        Assert.assertTrue(basePage.successMessageIsDisplayed());
    }

    @Test(priority = 14, groups = {"regression"})
    public void verifyOnGivingInvalidPasswordUserGetsRedirectedToOrderFailScreen() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        creditDebitCardPage.enterCardDetails();
        creditDebitCardPage.clickOnPayNowButton();
//        verifyEnteringValidCardDetailsAndClickingOnPayNow();
        bankPaymentPage.switchToBankPaymentFrame();
        bankPaymentPage.enterPassword(basePage.properties.getProperty("invalid-password"));
        bankPaymentPage.clickOnOkButton();
        bankPaymentPage.switchToBankPaymentWindow();
        bankPaymentPage.switchToBankPaymentFrame();
        Assert.assertTrue(bankPaymentPage.failMessageIsDisplayed());
    }

    @Test(priority = 15, groups = {"regression"})
    public void verifyOnClickingCancelUserGetsRedirectedToOrderFailScreen() {
        basePage.clickOnBuyNowButton();
        checkoutPage.clickOnCheckoutButton();
        orderSummaryPage.switchToOrderSummaryFrame();
        orderSummaryPage.clickOnContinueButton();
        selectPaymentPage.clickOnCreditDebitCardButton();
        creditDebitCardPage.enterCardDetails();
        creditDebitCardPage.clickOnPayNowButton();
//        verifyEnteringValidCardDetailsAndClickingOnPayNow();
        bankPaymentPage.switchToBankPaymentFrame();
        bankPaymentPage.clickOnCancelButton();
        bankPaymentPage.switchToBankPaymentWindow();
        bankPaymentPage.switchToBankPaymentFrame();
        Assert.assertTrue(bankPaymentPage.failMessageIsDisplayed());
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
