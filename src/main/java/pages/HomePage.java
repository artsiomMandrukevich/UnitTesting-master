package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By LOGIN_BUTTON = By.linkText("Log in");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LogInPage clicklogInButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return new LogInPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
