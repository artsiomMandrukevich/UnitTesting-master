package pages;

import dto.UserAccount;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By EMAIL_CREATE_ACCOUNT_INPUT = By.id("email_create");
    private static final By CREATE_ACCOUNT_BUTTON = By.id("SubmitCreate");

    private static final By EMAIL_SIGNIN_INPUT = By.id("email");
    private static final By PASSWORD_SIGNIN_INPUT = By.id("passwd");
    private static final By SIGNIN_BUTTON = By.id("SubmitLogin");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void fillCreateAccountEmail(String email) {
        driver.findElement(EMAIL_CREATE_ACCOUNT_INPUT).sendKeys(email);
    }

    public void fillSignInEmail(String email) {
        driver.findElement(EMAIL_SIGNIN_INPUT).sendKeys(email);
    }

    public void fillSignInPassword(String password) {
        driver.findElement(PASSWORD_SIGNIN_INPUT).sendKeys(password);
    }

    public void clickLogIn() {
        driver.findElement(SIGNIN_BUTTON).click();
    }

    public void clickCreateAccount() {
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
    }

    @Step("Fill out email, click Create Account button")
    public CreateAccountPage createAccount(String email) {
        fillCreateAccountEmail(email);
        clickCreateAccount();
        return new CreateAccountPage(driver);
    }

    @Step("Fill out email/password, click Sign in button")
    public MyAccountPage logIn(UserAccount userAccount) {
        fillSignInEmail(userAccount.getEmail());
        fillSignInPassword(userAccount.getPassword());
        clickLogIn();
        return new MyAccountPage(driver);
    }
}
