package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountLogInPage {

    WebDriver driver;

    @FindBy(linkText = "Log in")
    private WebElement LOGIN_BUTTON;

    public CreateAccountLogInPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public LogInPage logInButton() {
        LOGIN_BUTTON.click();
        return PageFactory.initElements(driver, LogInPage.class);
    }
}
