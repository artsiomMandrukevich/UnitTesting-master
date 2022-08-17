package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    WebDriver driver;

    @FindBy(id = "passp-field-login")
    private WebElement USERNAME_INPUT;
    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement SIGNIN_BUTTON;
    @FindBy(id = "passp-field-passwd")
    private WebElement PASSWORD_INPUT;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String userName) {
        USERNAME_INPUT.sendKeys(userName);
    }

    public void enterPassword(String password) {
        PASSWORD_INPUT.sendKeys(password);
    }

    public void clickSignInButton() {
        SIGNIN_BUTTON.click();
    }

    public PersonalAccountPage loginIntoPersonalAccount(String userName, String password) {
        enterUserName(userName);
        clickSignInButton();
        enterPassword(password);
        clickSignInButton();
        return PageFactory.initElements(driver, PersonalAccountPage.class);
    }
}
