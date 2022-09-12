package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import store.Product;

import java.util.*;

public class CartPage extends BasePage {

    private static final By CART_ITEMS = By.cssSelector(".cart_item");
    private static final By CART_ITEM_PRICE = By.cssSelector(".price .price");
    private static final By CART_ITEM_TOTAL = By.cssSelector(".cart_total .price");
    private static final By CART_ITEMS_SHIPPING_PRICE = By.cssSelector("td[class='price'][id='total_shipping']");
    private static final By CART_ITEMS_TAX_PRICE = By.cssSelector("td[class='price'][id='total_tax']");
    private static final By CART_ITEMS_FULL_TOTAL = By.cssSelector("tr[class='cart_total_price'] span[id='total_price']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public float getTotalShippingPrice() {
        return Float.parseFloat(driver.findElement(CART_ITEMS_SHIPPING_PRICE).getText().replace("$", ""));
    }

    public float getTotalTaxPrice() {
        return Float.parseFloat(driver.findElement(CART_ITEMS_TAX_PRICE).getText().replace("$", ""));
    }

    public float getTotalPrice() {
        return Float.parseFloat(driver.findElement(CART_ITEMS_FULL_TOTAL).getText().replace("$", ""));
    }

    @Step("Check the cart contains product by name and price")
    public boolean checkProductPrice(Product product) {
        WebElement cartItem = getCartItemBy(product.getProductName());
        WebElement cartItemPrice = cartItem.findElement(CART_ITEM_PRICE);
        return cartItemPrice.getText().contains(String.valueOf(product.getProductPrice()));
    }

    private WebElement getCartItemBy(String productName) {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);
        return cartItems.stream().filter(cartItem -> cartItem.getText().contains(productName)).findFirst().get();
    }

    @Step("Check total calculation")
    public boolean checkTotalCalculation() {
        float totalShippingPrice = getTotalShippingPrice();
        float totalTaxPrice = getTotalTaxPrice();
        float totalPrice = getTotalPrice();
        Float calculateTotalPrice;
        List<WebElement> products = driver.findElements(CART_ITEMS);
        float itemsTotal = (float) products.stream().mapToDouble(row -> Double.parseDouble(row
                .findElement(CART_ITEM_TOTAL).getText()
                .replace("$", ""))).sum();
        calculateTotalPrice = totalPrice - totalTaxPrice - totalShippingPrice - itemsTotal;
        calculateTotalPrice = (float) Math.round(calculateTotalPrice);
        return calculateTotalPrice.equals((float) 0);
    }
}
