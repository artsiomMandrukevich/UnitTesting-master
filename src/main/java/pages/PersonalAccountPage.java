package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage extends BasePage {

    private static final By USER_ACCOUNT_NAME = By.xpath("//a[@href='https://passport.yandex.com']/div[@class='user-pic user-pic_has-plus_ user-account__pic']");
    private static final By LOG_OUT_BUTTON = By.xpath("//span[text()='Log out']");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName() {
        waiter.waitForElementVisibility(USER_ACCOUNT_NAME);
        return driver.findElement(USER_ACCOUNT_NAME).getText();
    }

    public void clickUserName() {
        driver.findElement(USER_ACCOUNT_NAME).click();
    }

    public void clickLogOut() {
        driver.findElement(LOG_OUT_BUTTON).click();
    }

    @Step("Click Log Out button")
    public HomePage logOut() {
        waiter.waitForElementVisibility(USER_ACCOUNT_NAME);
        this.clickUserName();
        this.clickLogOut();
        return new HomePage(driver);
    }
}
