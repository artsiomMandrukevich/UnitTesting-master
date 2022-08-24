package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("passp-field-login");
    private static final By SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By PASSWORD_INPUT = By.id("passp-field-passwd");

    public LogInPage(WebDriver driver) {
        super(driver);
    }

    public void enterUserName(String userName) {
        driver.findElement(USERNAME_INPUT).sendKeys(userName);
    }

    public void enterPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickSignInButton() {
        driver.findElement(SIGNIN_BUTTON).click();
    }

    @Step("Fill out credentials, log in into the personal area")
    public PersonalAccountPage logIn(String userName, String password) {
        enterUserName(userName);
        clickSignInButton();
        enterPassword(password);
        clickSignInButton();
        return new PersonalAccountPage(driver);
    }

}
