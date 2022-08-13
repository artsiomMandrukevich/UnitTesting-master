import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserWaiting {
    private static WebDriver driver;

    private static final By GET_USER_BUTTON = By.id("save");
    private static final By IMAGE_USER = By.cssSelector("#loading img[src*='randomuser']");

    @BeforeEach
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void waitForUser() {
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
        WebElement webElement = driver.findElement(GET_USER_BUTTON);
        webElement.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(IMAGE_USER));
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
