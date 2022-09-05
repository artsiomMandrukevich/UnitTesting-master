package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import store.Cart;
import store.Product;

import java.util.List;

public class WomenCategoryPage extends BasePage {

    Cart cart = Cart.getInstance();

    private static final By LIST_VIEW_BUTTON = By.cssSelector("li[id='list']");
    private static final By PRODUCTS_TABLE = By.xpath("//ul[@class='product_list row list']//li[contains(@class, 'ajax_block_product')]");
    private static final By PRICE_PRODUCT_VALUE = By.cssSelector(".right-block-content .content_price .product-price");
    private static final By NAME_PRODUCT_VALUE = By.cssSelector(".center-block h5 a");
    private static final By ADD_PRODUCT_TO_CART_BUTTON = By.cssSelector(".right-block-content .ajax_add_to_cart_button");
    private static final By CONTINUE_SHOPPING_BUTTON = By.cssSelector("span[title='Continue shopping']");
    private static final By GO_TO_CART_PAGE_BUTTON = By.cssSelector("a[title='View my shopping cart']");

    public WomenCategoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueShoppingButton() {
        waiter.waitForElementVisibility(CONTINUE_SHOPPING_BUTTON);
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public void switchToListView() {
        driver.findElement(LIST_VIEW_BUTTON).click();
    }

    @Step("Add product to Cart")
    public WomenCategoryPage addProductToCart() {
        switchToListView();
        List<WebElement> productRows = driver.findElements(PRODUCTS_TABLE);
        for (int i = 0; i < 3; i++) {
            Float priceProduct = Float.valueOf(productRows.get(i)
                    .findElement(PRICE_PRODUCT_VALUE).getText()
                    .replace("$", ""));
            String nameProduct = productRows.get(i).findElement(NAME_PRODUCT_VALUE).getText();
            cart.setProductToCart(new Product(nameProduct, priceProduct));
            productRows.get(i).findElement(ADD_PRODUCT_TO_CART_BUTTON).click();
            clickContinueShoppingButton();
        }
        return this;
    }

    @Step("Go to Cart page")
    public CartPage goToCartPage() {
        driver.findElement(GO_TO_CART_PAGE_BUTTON).click();
        return new CartPage(driver);
    }
}
