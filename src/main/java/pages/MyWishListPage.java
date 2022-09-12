package pages;

import helpers.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyWishListPage extends BasePage {

    private static final String NAME_WISHLIST = "IsSoft wishlist";
    private static final By RANDOM_PRODUCT_LINK = By.cssSelector(".block_content .products-block a");
    private static final By WISHLIST_TABLE_ROWS = By.xpath("//tbody//tr");
    private static final By WISHLIST_NAME_INPUT = By.cssSelector("#name");
    private static final By WISHLIST_QTY_PRODUCTS = By.cssSelector(".bold");
    private static final By WISHLIST_REMOVE_ICON = By.cssSelector(".wishlist_delete a");
    private static final By WISHLIST_SAVE_BUTTON = By.cssSelector("#submitWishlist");

    Waiter waiter = new Waiter(driver);

    public MyWishListPage(WebDriver driver) {
        super(driver);
    }

    public void fillName() {
        driver.findElement(WISHLIST_NAME_INPUT).sendKeys(NAME_WISHLIST);
    }

    public void clickSave() {
        driver.findElement(WISHLIST_SAVE_BUTTON).click();
    }

    public int getWishListsCount() {
        List<WebElement> rows = driver.findElements(WISHLIST_TABLE_ROWS);
        return rows.size();
    }

    public int getWishListsQTY() {
        return Integer.parseInt(driver.findElement(WISHLIST_QTY_PRODUCTS).getText());
    }

    public void removeItemFromWishList() {
        driver.findElement(WISHLIST_REMOVE_ICON).click();
    }

    @Step("Ensure that the WishList is empty. Remove rows is needed.")
    public MyWishListPage ensureWishListsIsEmpty() {
        int actualCountOfWishLists = driver.findElements(WISHLIST_TABLE_ROWS).size();
        while (actualCountOfWishLists > 0) {
            removeItemFromWishList();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            actualCountOfWishLists = actualCountOfWishLists - 1;
            waiter.waitNumberOfElementsToBe(WISHLIST_TABLE_ROWS, actualCountOfWishLists);
        }
        return this;
    }

    @Step("Go to randomly product page")
    public ProductPage goToProductDetailPage() {
        driver.findElement(RANDOM_PRODUCT_LINK).click();
        return new ProductPage(driver);
    }

    @Step("Create My WishList")
    public MyWishListPage createWishList() {
        fillName();
        clickSave();
        waiter.waitNumberOfElementsToBe(WISHLIST_TABLE_ROWS, 1);
        return this;
    }

    @Step("Check the product was added to WishList")
    public boolean checkProductWasAddedToWishList() {
        return getWishListsCount() == 1 && getWishListsQTY() == 1;
    }
}
