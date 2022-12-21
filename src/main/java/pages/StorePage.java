package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import store.Product;

import java.util.List;

public class StorePage extends BasePage {

    private static final By LIST_VIEW_BUTTON = By.cssSelector("li[id='list']");
    private static final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("span[title='Continue shopping']");
    private static final By GO_TO_CART_PAGE_BUTTON = By.cssSelector("a[title='View my shopping cart']");
    private static final By PRODUCT_ITEMS = By.cssSelector("ul.product_list li.ajax_block_product");
    private static final By PRODUCT_ITEM_NAME = By.cssSelector(".center-block h5 a");
    private static final By PRODUCT_ITEM_PRICE = By.cssSelector(".right-block-content .content_price .product-price");
    private static final By PRODUCT_ITEM_ADD_BUTTON = By.cssSelector(".right-block-content .ajax_add_to_cart_button");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueShopping() {
        waiter.waitForElementVisibility(CONTINUE_SHOPPING_BUTTON);
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void switchToListView() {
        driver.findElement(LIST_VIEW_BUTTON).click();
    }

    @Step("Switch to list view")
    public StorePage switchListView() {
        switchToListView();
        return this;
    }

    @Step("Add random product to Cart")
    public Product addRandomProductToCart() {
        List<WebElement> productRows = driver.findElements(PRODUCT_ITEMS);
        int sizeTable = productRows.size();
        int rowNumber = (int) (Math.random() * sizeTable);
        Float priceProduct = Float.valueOf(productRows.get(rowNumber)
                .findElement(PRODUCT_ITEM_PRICE).getText()
                .replace("$", ""));
        String nameProduct = productRows.get(rowNumber).findElement(PRODUCT_ITEM_NAME).getText();
        productRows.get(rowNumber).findElement(PRODUCT_ITEM_ADD_BUTTON).click();
        clickContinueShopping();
        return new Product(nameProduct, priceProduct);
    }


    @Step("Go to Cart page")
    public CartPage goToCartPage() {
        driver.findElement(GO_TO_CART_PAGE_BUTTON).click();
        return new CartPage(driver);
    }
}
