package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage extends HomePage {

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

    public PersonalAccountPage loginToPersonalArea(String userName, String password) {
        enterUserName(userName);
        clickSignInButton();
        enterPassword(password);
        clickSignInButton();
        return new PersonalAccountPage(driver);
    }
}
