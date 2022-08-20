package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private final WebDriver driver;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementVisibility(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(765)).until(
                ExpectedConditions.visibilityOf(element)
        );
    }
}
