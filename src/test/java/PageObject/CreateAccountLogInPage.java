package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAccountLogInPage {

    WebDriver driver;

    private static final By LOGIN_BUTTON = By.linkText("Log in");

    public CreateAccountLogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public LogInPage logInButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return new LogInPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
