package Pages;

import Utilities.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BasePage extends Utils {

    public WebDriver driver;
    public FileInputStream fis;
    public Properties properties;

    public BasePage(WebDriver driver1) {
        this.driver = driver1;
        PageFactory.initElements(driver, this);
        properties = new Properties();
        try {
            fis = new FileInputStream("resources/config.properties");
            properties.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FindBy(xpath = "//a[@class='btn buy']")
    WebElement BuyNowButton;

    @FindBy(xpath = "//div[@class='trans-status trans-success']")
    WebElement SuccessMessage;

    public void clickOnBuyNowButton() {
        click(BuyNowButton);
        holdExecution(2);
    }

    public boolean successMessageIsDisplayed(){
        return isDisplayed(SuccessMessage);
    }

}
