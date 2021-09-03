package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[@class='cart-section']/table//tbody)[1]/tr/td")
    List<WebElement> ShoppingCartTableDetails;

    @FindBy(xpath = "//div[@class='cart-content buying']")
    WebElement CheckoutPopUp;

    @FindBy(xpath = "(//div[@class='cart-section'])[2]//td")
    List<WebElement> CustomerDetails;

    @FindBy(xpath = "(//div[@class='cart-section'])[2]//td/input")
    List<WebElement> CustomerDetailsInputBox;

    @FindBy(xpath = "(//div[@class='cart-section'])[2]//td/textarea")
    WebElement AddressDetailOfCustomer;

    @FindBy(xpath = "//div[@class='cart-checkout']")
    WebElement CheckoutButton;

//    public List<String> listOfShoppingCartElements() {
//        List<String> elements = new ArrayList<>();
//        elements.add("Midtrans Pillow");
//        elements.add("× 1");
//        elements.add(null);
//        elements.add(null);
//        elements.add("Total");
//        elements.add("20,000");
//        return elements;
//    }

    public List<String> listOfCustomerDetails() {
        List<String> elements = new ArrayList<>();
        elements.add("Name");
        elements.add("");
        elements.add("Email");
        elements.add("");
        elements.add("Phone no");
        elements.add("");
        elements.add("City");
        elements.add("");
        elements.add("Address");
        elements.add("MidPlaza 2, 4th Floor Jl.Jend.Sudirman Kav.10-11");
        elements.add("Postal Code");
        elements.add("");
        return elements;
    }

    public List<String> listOfCustomerDetailsToEnter() {
        List<String> elements = new ArrayList<>();
        elements.add(properties.getProperty("customerName"));
        elements.add(properties.getProperty("customerEmail"));
        elements.add(properties.getProperty("customerPhoneNo"));
        elements.add(properties.getProperty("customerCity"));
        elements.add(properties.getProperty("customerPostalCode"));
        return elements;
    }

    String address = properties.getProperty("customerAddress");


    public boolean shoppingCartDetailsAreCorrect() {
        return areDisplayed(ShoppingCartTableDetails);
    }

    public boolean checkoutPopUpIsDisplayed() {
        return isDisplayed(CheckoutPopUp);
    }

    public boolean CustomerDetailsAreCorrect() {
        return arePresent(CustomerDetails, listOfCustomerDetails());
    }

    public void enterCustomerDetails() {
        enterTextForListOfElements(CustomerDetailsInputBox, listOfCustomerDetailsToEnter());
        enterText(AddressDetailOfCustomer, address);
        holdExecution(2);
    }

    public void clickOnCheckoutButton() {
        click(CheckoutButton);
    }

}
