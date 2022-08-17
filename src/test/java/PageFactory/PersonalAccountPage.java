package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "a.user-account_left-name .user-account__name")
    private WebElement USER_ACCOUNT_NAME;
    @FindBy(xpath = "//span[text()='Log out']")
    private WebElement LOG_OUT_BUTTON;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(765));
    }

    public void waitVisibilityOfElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getUserName() {
        this.waitVisibilityOfElement(USER_ACCOUNT_NAME);
        return USER_ACCOUNT_NAME.getText();
    }

    public void clickUserName() {
        USER_ACCOUNT_NAME.click();
    }

    public void clickLogOut() {
        LOG_OUT_BUTTON.click();
    }

    public CreateAccountLogInPage logOut() {
        this.clickUserName();
        this.clickLogOut();
        return PageFactory.initElements(driver, CreateAccountLogInPage.class);
    }
}
