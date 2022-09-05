package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import store.Cart;
import store.Product;

import java.util.*;
import java.util.stream.IntStream;

public class CartPage extends BasePage {

    Cart cart = Cart.getInstance();
    List<Product> actualProductList = new ArrayList<>();

    private static final By CART_SUMMARY_TABLE = By.xpath("//table[@id='cart_summary']/tbody//tr");
    private static final By PRODUCT_PRICE_VALUE = By.cssSelector(".cart_total .price");
    private static final By PRODUCT_NAME_VALUE = By.cssSelector(".product-name a");
    private static final By TOTAL_PRODUCT_PRICE_VALUE = By.cssSelector("td[class='price'][id='total_product']");
    private static final By TOTAL_SHIPPING_PRICE_VALUE = By.cssSelector("td[class='price'][id='total_shipping']");
    private static final By TOTAL_TAX_PRICE_VALUE = By.cssSelector("td[class='price'][id='total_tax']");
    private static final By TOTAL_PRICE_VALUE = By.cssSelector("tr[class='cart_total_price'] span[id='total_price']");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public float getTotalProductsPrice() {
        return Float.parseFloat(driver.findElement(TOTAL_PRODUCT_PRICE_VALUE).getText().replace("$", ""));
    }

    public float getTotalShippingPrice() {
        return Float.parseFloat(driver.findElement(TOTAL_SHIPPING_PRICE_VALUE).getText().replace("$", ""));
    }

    public float getTotalTaxPrice() {
        return Float.parseFloat(driver.findElement(TOTAL_TAX_PRICE_VALUE).getText().replace("$", ""));
    }

    public float getTotalPrice() {
        return Float.parseFloat(driver.findElement(TOTAL_PRICE_VALUE).getText().replace("$", ""));
    }

    public void setActualProductList() {
        List<WebElement> productRows = driver.findElements(CART_SUMMARY_TABLE);
        productRows.forEach(row -> {
            Float productPrice = Float.valueOf(row
                    .findElement(PRODUCT_PRICE_VALUE).getText()
                    .replace("$", ""));
            String productname = row.findElement(PRODUCT_NAME_VALUE).getText();
            actualProductList.add(new Product(productname, productPrice));
        });
    }

    public boolean compareActualAndExpectedProductList() {
        setActualProductList();
        boolean isProductNameCorrect = IntStream.range(0, cart.getCartList().size())
                .allMatch(i -> cart.getCartList().get(i).getProductName()
                        .contains(actualProductList.get(i).getProductName()));
        boolean isProductPriceCorrect = IntStream.range(0, cart.getCartList().size())
                .allMatch(i -> cart.getCartList().get(i).getProductPrice()
                        .equals(actualProductList.get(i).getProductPrice()));
        return isProductNameCorrect && isProductPriceCorrect;
    }

    public boolean compareActualAndExpectedTotalPrice() {
        float totalProductsPrice = getTotalProductsPrice();
        float totalShippingPrice = getTotalShippingPrice();
        float totalTaxPrice = getTotalTaxPrice();
        float totalPrice = getTotalPrice();
        Float calculateTotalPrice;
        calculateTotalPrice = totalPrice - totalTaxPrice - totalShippingPrice - totalProductsPrice;
        boolean compareTotalPrice = calculateTotalPrice.equals((float) 0);
        boolean compareTotalProductsPrice = cart.getTotalPrice().equals(totalProductsPrice);
        return compareTotalProductsPrice && compareTotalPrice;
    }

    @Step("Compare products and prices in cart")
    public boolean comparePriceAndProductsInCart() {
        return compareActualAndExpectedProductList() && compareActualAndExpectedTotalPrice();
    }
}
