package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {

    public void clickButton(WebDriver driver, By locator) {
        WebElement webElement = driver.findElement(locator);
        webElement.click();
    }

    public void sendKeys(WebDriver driver, By locator, String keys) {
        WebElement passwordInput = driver.findElement(locator);
        passwordInput.sendKeys(keys);
    }
}
