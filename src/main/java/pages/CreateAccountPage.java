package pages;

import helpers.Element;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountPage extends BasePage {

    private static final By FIRST_NAME_PERSONAL_INPUT = By.id("customer_firstname");
    private static final By LAST_NAME_PERSONAL_INPUT = By.id("customer_lastname");
    private static final By PASSWORD_INPUT = By.id("passwd");

    private static final By ADDRESS_LINE_1_INPUT = By.id("address1");
    private static final By CITY_INPUT = By.id("city");
    private static final By STATE_SELECT = By.id("id_state");
    private static final By ZIP_CODE_INPUT = By.id("postcode");
    private static final By MOBILE_PHONE_INPUT = By.id("phone_mobile");
    private static final By ALIAS_INPUT = By.id("alias");

    private static final By REGISTER_BUTTON = By.id("submitAccount");

    Element element = new Element(driver);

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill out the Personal Info")
    public void fillOutPersonalInfo(String firstName, String lastName, String password) {
        element.sendKeys(FIRST_NAME_PERSONAL_INPUT, firstName);
        element.sendKeys(LAST_NAME_PERSONAL_INPUT, lastName);
        element.sendKeys(PASSWORD_INPUT, password);
    }

    @Step("Fill out the Address Info")
    public void fillOutAddressInfo(String addressLineFirst, String city, String state,
                                   String zipCode, String mobilePhone, String alias) {
        element.sendKeys(ADDRESS_LINE_1_INPUT, addressLineFirst);
        element.sendKeys(CITY_INPUT, city);
        element.selectByVisibleText(STATE_SELECT, state);
        element.sendKeys(ZIP_CODE_INPUT, zipCode);
        element.sendKeys(MOBILE_PHONE_INPUT, mobilePhone);
        element.clearInput(ALIAS_INPUT);
        element.sendKeys(ALIAS_INPUT, alias);
    }

    @Step("Click Register button")
    public MyAccountPage clickRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
        return new MyAccountPage(driver);
    }
}


