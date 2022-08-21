package pages;

import helpers.Screenshot;
import helpers.Waiter;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected Waiter waiter;
    protected Screenshot screenshot;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new Waiter(driver);
        this.screenshot = new Screenshot(driver);
    }
}
