import helpers.Element;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserWaiting {
    Element element = new Element();

    private static WebDriver driver;

    private static final By GET_USER_BUTTON = By.id("save");
    private static final By IMAGE_USER = By.cssSelector("#loading img");

    @Before
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void waitForUser() {
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");

        element.clickButton(driver, GET_USER_BUTTON);
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1)).until(ExpectedConditions
                .visibilityOfElementLocated(IMAGE_USER));
    }

    @After
    public void stopDriver() {
        driver.quit();
    }
}
