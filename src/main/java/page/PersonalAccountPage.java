package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonalAccountPage extends BasePage {

    @FindBy(css = "a.user-account_left-name .user-account__name")
    private WebElement userAccountName;

    @FindBy(xpath = "//span[text()='Log out']")
    private WebElement logOutButton;

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getUserName() {
        waiter.waitForElementVisibility(userAccountName);
        return userAccountName.getText();
    }

    public void clickUserName() {
        userAccountName.click();
    }

    public void clickLogOut() {
        logOutButton.click();
    }

    public HomePage logOut() {
        this.clickUserName();
        this.clickLogOut();
        return new HomePage(driver);
    }
}
