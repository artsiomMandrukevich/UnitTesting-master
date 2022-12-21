package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Element {

    private final WebDriver driver;

    public Element(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeys(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    public void selectByVisibleText(By locator, String value) {
        Select dropDown = new Select(driver.findElement(locator));
        dropDown.selectByVisibleText(value);
    }

    public void clearInput(By locator) {
        driver.findElement(locator).clear();
    }
}
