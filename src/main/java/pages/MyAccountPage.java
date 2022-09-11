package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    private static final By USER_ACCOUNT_NAME = By.cssSelector(".account span");
    private static final By MY_WISH_LISTS_BUTTON = By.cssSelector("a[title='My wishlists']");
    private static final By WOMEN_CATEGORY_LINK = By.cssSelector("a[title='Women']");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getAccountName() {
        waiter.waitForElementVisibility(USER_ACCOUNT_NAME);
        return driver.findElement(USER_ACCOUNT_NAME).getText();
    }

    @Step("Go to My WishLists page")
    public MyWishListPage goToMyWishListPage() {
        driver.findElement(MY_WISH_LISTS_BUTTON).click();
        return new MyWishListPage(driver);
    }

    @Step("Go to Women Category page")
    public StorePage goToStorePage() {
        driver.findElement(WOMEN_CATEGORY_LINK).click();
        return new StorePage(driver);
    }
}
