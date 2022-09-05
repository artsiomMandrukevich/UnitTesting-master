package pages;

import helpers.Waiter;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyWishListPage extends BasePage {

    private static final String NAME_OF_YOUR_WISHLIST = "IsSoft wishlist";
    private static final By NAME_WISHLIST_INPUT = By.cssSelector("#name");
    private static final By SAVE_WISHLIST_BUTTON = By.cssSelector("#submitWishlist");
    private static final By WISHLIST_TABLE_ROWS = By.xpath("//tbody//tr");
    private static final By QTY_PRODUCT_CELL = By.xpath("//tbody//tr[1]/td[2]");
    private static final By REMOVE_WISHLIST_ICON = By.xpath("//tbody/tr/td[@class='wishlist_delete']/a");
    private static final By RANDOM_PRODUCT_LINK = By.xpath("//ul[@class='block_content products-block']/li[1]/a");

    Waiter waiter = new Waiter(driver);

    public MyWishListPage(WebDriver driver) {
        super(driver);
    }

    public void fillOutNameOfYourWishList() {
        driver.findElement(NAME_WISHLIST_INPUT).sendKeys(NAME_OF_YOUR_WISHLIST);
    }

    public void clickSaveWishList() {
        driver.findElement(SAVE_WISHLIST_BUTTON).click();
    }

    public int getCountRowsOfWishLists() {
        List<WebElement> rows = driver.findElements(WISHLIST_TABLE_ROWS);
        return rows.size();
    }

    public int getCountQTYProductForFirstRow() {
        return Integer.parseInt(driver.findElement(QTY_PRODUCT_CELL).getText());
    }

    public void removeItemFromWishList() {
        driver.findElement(REMOVE_WISHLIST_ICON).click();
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
        fillOutNameOfYourWishList();
        clickSaveWishList();
        waiter.waitNumberOfElementsToBe(WISHLIST_TABLE_ROWS, 1);
        return this;
    }

    @Step("Check the product was added to WishList")
    public boolean checkProductWasAddedToWishList() {
        return getCountRowsOfWishLists() == 1 && getCountQTYProductForFirstRow() == 1;
    }
}
