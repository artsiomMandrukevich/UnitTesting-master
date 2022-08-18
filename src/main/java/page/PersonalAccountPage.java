package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalAccountPage extends BasePage {

    @FindBy(css = "a.user-account_left-name .user-account__name")
    private WebElement USER_ACCOUNT_NAME;

    @FindBy(xpath = "//span[text()='Log out']")
    private WebElement LOG_OUT_BUTTON;

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUserName() {
        waiter.waitForElementVisibility(USER_ACCOUNT_NAME);
        return USER_ACCOUNT_NAME.getText();
    }

    public void clickUserName() {
        USER_ACCOUNT_NAME.click();
    }

    public void clickLogOut() {
        LOG_OUT_BUTTON.click();
    }

    public HomePage logOut() {
        this.clickUserName();
        this.clickLogOut();
        return new HomePage(driver);
    }
}
