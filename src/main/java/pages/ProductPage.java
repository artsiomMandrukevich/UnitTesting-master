package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private static final By ADD_TO_WISHLIST_BUTTON = By.cssSelector("#wishlist_button");
    private static final By CLOSE_NOTIFICATION_BUTTON = By.cssSelector("a[title='Close']");
    private static final By FANCY_BOX_OVERLAY = By.cssSelector(".fancybox-overlay-fixed");
    private static final By GO_TO_MY_ACCOUNT_BUTTON = By.cssSelector(".header_user_info a.account");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToWishList() {
        driver.findElement(ADD_TO_WISHLIST_BUTTON).click();
    }

    public void closeNotificationWindow() {
        driver.findElement(CLOSE_NOTIFICATION_BUTTON).click();
    }

    @Step("Add product to WishList")
    public ProductPage addProductToWishList() {
        clickAddToWishList();
        closeNotificationWindow();
        return this;
    }

    @Step("Go to MyAccount page")
    public MyAccountPage goToMyAccountPage() {
        waiter.waitForElementToBeClickable(FANCY_BOX_OVERLAY);
        driver.findElement(GO_TO_MY_ACCOUNT_BUTTON).click();
        return new MyAccountPage(driver);
    }
}
