package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BankPaymentPage extends BasePage {

    public BankPaymentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    @FindBy(xpath = "//div[@class=\"page-container scroll\"]//iframe")
//    WebElement IframeElement;

    @FindBy(xpath = "//div[@class='form-group']")
    List<WebElement> BankPaymentDetails;

    @FindBy(xpath = "//input[@class='form-control input-xs']")
    WebElement PasswordBox;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-success']")
    WebElement OkButton;

//    @FindBy(xpath = "//div[@class='text-failed text-bold']")
//    WebElement FailMessage;

    @FindBy(xpath = "//div[@class='final-panel failed']")
    WebElement FailScreen;

    @FindBy(xpath = "//button[@class='btn btn-sm btn-danger']")
    WebElement CancelButton;

    public boolean detailsAreDisplayedOnBankPaymentScreen() {
        return areDisplayed(BankPaymentDetails);
    }

    public void switchToBankPaymentFrame() {
        switchToFrame(0);
    }

    public void enterPassword(String password){
        enterText(PasswordBox, password);
    }

    public void clickOnOkButton(){
        click(OkButton);
        holdExecution(4);
    }

    public boolean failMessageIsDisplayed(){
        return isDisplayed(FailScreen);
    }

    public void clickOnCancelButton(){
        click(CancelButton);
        holdExecution(4);
    }

    public void switchToBankPaymentWindow(){
        switchToWindowHandle();
    }
}
