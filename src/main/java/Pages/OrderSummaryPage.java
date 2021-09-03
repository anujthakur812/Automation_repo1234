package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class OrderSummaryPage extends BasePage {

    public OrderSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='app-container']")
    WebElement OrderSummaryPopUp;

    @FindBy(xpath = "//td[@class='table-item text-body']/../td")
    List<WebElement> OrderDetails;

    @FindBy(xpath = "//a[@class='button-main-content']")
    WebElement ContinueButton;

    public List<String> listOfOrderDetails() {
        List<String> elements = new ArrayList<>();
        elements.add("Midtrans Pillow");
        elements.add("20,000");
        return elements;
    }

    String id = "snap-midtrans";

    public void switchToOrderSummaryFrame() {
        switchToFrame(id);
    }

    public boolean orderSummaryPopUpIsVisible() {
        return isDisplayed(OrderSummaryPopUp);
    }

    public boolean orderDetailsArePresent() {
        return arePresent(OrderDetails, listOfOrderDetails());
    }

    public void clickOnContinueButton() {
        click(ContinueButton);
        holdExecution(2);
    }

}
