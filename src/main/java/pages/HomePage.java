package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By LOGIN_BUTTON = By.linkText("Log in");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    @Step("Go to Home page, click LogIn button")
    public LogInPage clicklogInButton() {
        driver.findElement(LOGIN_BUTTON).click();
        return new LogInPage(driver);
    }
}
