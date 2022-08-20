package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends BasePage {

    @FindBy(id = "passp-field-login")
    private WebElement usernameInput;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement signInButton;

    @FindBy(id = "passp-field-passwd")
    private WebElement passwordInput;

    public LogInPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String userName) {
        usernameInput.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public PersonalAccountPage logIn(String userName, String password) {
        enterUserName(userName);
        clickSignInButton();
        enterPassword(password);
        clickSignInButton();
        return new PersonalAccountPage(driver);
    }
}
