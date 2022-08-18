package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends BasePage {

    @FindBy(id = "passp-field-login")
    private WebElement USERNAME_INPUT;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement SIGNIN_BUTTON;

    @FindBy(id = "passp-field-passwd")
    private WebElement PASSWORD_INPUT;

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
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

    public PersonalAccountPage logIn(String userName, String password) {
        enterUserName(userName);
        clickSignInButton();
        enterPassword(password);
        clickSignInButton();
        return new PersonalAccountPage(driver);
    }
}
