package pages;

import helpers.Waiter;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected Waiter waiter;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
    }
}
