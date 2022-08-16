package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    WebDriver driver;

    private static final By USER_ACCOUNT_NAME = By.cssSelector("a.user-account_left-name .user-account__name");
    private static final By LOG_OUT_BUTTON = By.xpath("//span[text()='Log out']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(765)).until(ExpectedConditions
                .visibilityOfElementLocated(USER_ACCOUNT_NAME));
    }

    public String getUserName() {
        return driver.findElement(USER_ACCOUNT_NAME).getText();
    }

    public void clickUserName() {
        driver.findElement(USER_ACCOUNT_NAME).click();
    }

    public void clickLogOut() {
        driver.findElement(LOG_OUT_BUTTON).click();
    }

    public CreateAccountLogInPage logOut() {
        this.clickUserName();
        this.clickLogOut();
        return new CreateAccountLogInPage(driver);
    }


}
