package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class HomePage extends BasePage {

    private static final By LOGIN_BUTTON = By.linkText("Log in");
    private static final String SCREENSHOT_NAME = "homepage";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LogInPage clicklogInButton() throws IOException {
        screenshot.captureScreenshot(SCREENSHOT_NAME);
        driver.findElement(LOGIN_BUTTON).click();
        return new LogInPage(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
