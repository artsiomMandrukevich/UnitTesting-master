package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import store.Product;
import io.qameta.allure.Allure;

import java.util.*;

public class CartPage extends BasePage {

    private Float totalProductPrice = (float) 0;

    private static final By CART_SUMMARY_TABLE = By.xpath("//table[@id='cart_summary']/tbody//tr");
    private static final By PRODUCT_TOTAL_PRICE_VALUE = By.cssSelector(".cart_total .price");
    private static final By TOTAL_SHIPPING_PRICE_VALUE = By.cssSelector("td[class='price'][id='total_shipping']");
    private static final By TOTAL_TAX_PRICE_VALUE = By.cssSelector("td[class='price'][id='total_tax']");
    private static final By TOTAL_PRICE_VALUE = By.cssSelector("tr[class='cart_total_price'] span[id='total_price']");

    public CartPage(WebDriver driver) {
        super(driver);
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

    @Step("Check the cart contains our item")
    public boolean checkProduct(Product product) {
        String trRowByProductName = "//table[@id='cart_summary']/tbody//tr//p[@class='product-name']//a[text()='"
                + product.getProductName()
                + "']/../../..";
        By tdProductPrice = By.xpath(trRowByProductName
                + "//span[@class='price']/span[@class='price' or @class='price special-price']");
        Float actualProductPrice = Float.valueOf(driver
                .findElement(tdProductPrice)
                .getText()
                .replace("$", ""));
        Allure.addAttachment("productName", String.valueOf(product.getProductName()));
        Allure.addAttachment("actualProductPrice", String.valueOf(actualProductPrice));
        Allure.addAttachment("expectedProductPrice", String.valueOf(product.getProductPrice()));
        return actualProductPrice.equals(product.getProductPrice());
    }

    @Step("Check total price")
    public boolean checkTotalPrice() {
        float totalShippingPrice = getTotalShippingPrice();
        float totalTaxPrice = getTotalTaxPrice();
        float totalPrice = getTotalPrice();
        Float calculateTotalPrice;
        List<WebElement> productRows = driver.findElements(CART_SUMMARY_TABLE);
        productRows.forEach(row -> {
            float productTotalPrice = Float.parseFloat(row
                    .findElement(PRODUCT_TOTAL_PRICE_VALUE).getText()
                    .replace("$", ""));
            totalProductPrice += productTotalPrice;
        });
        calculateTotalPrice = totalPrice - totalTaxPrice - totalShippingPrice - totalProductPrice;
        calculateTotalPrice = (float) Math.round(calculateTotalPrice);
        Allure.addAttachment("totalProductPrice", String.valueOf(totalProductPrice));
        Allure.addAttachment("totalShippingPrice", String.valueOf(totalShippingPrice));
        Allure.addAttachment("totalTaxPrice", String.valueOf(totalTaxPrice));
        Allure.addAttachment("totalPrice", String.valueOf(totalPrice));
        Allure.addAttachment("calculateTotalPrice", String.valueOf(calculateTotalPrice));
        return calculateTotalPrice.equals((float) 0);
    }

}
