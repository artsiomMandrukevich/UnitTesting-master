package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocatorsExample {

    private WebDriver driver;

    private final By byId = By.id("passp-field-login");
    private final By byName = By.name("login");
    private final By byClassName = By.className("AuthSocialBlock-title");
    private final By byTagName = By.tagName("button");
    private final By byLinkText = By.linkText("Help and support");
    private final By byPartialLinkTest = By.partialLinkText("Help");
    private final By byCSS = By.cssSelector("button[id='passp:sign-in']");
    private final By byXPath = By.xpath("//button[@id='passp:sign-in']");

    private void checkElementIsVisibleByLocator(By locator) {
        WebElement elementByID = driver.findElement(locator);
        assertTrue(elementByID.isDisplayed());
    }

    @BeforeEach
    void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void locatorsExample() {
        driver.get("https://passport.yandex.com/auth");
        checkElementIsVisibleByLocator(byId);
        checkElementIsVisibleByLocator(byName);
        checkElementIsVisibleByLocator(byClassName);
        checkElementIsVisibleByLocator(byTagName);
        checkElementIsVisibleByLocator(byLinkText);
        checkElementIsVisibleByLocator(byPartialLinkTest);
        checkElementIsVisibleByLocator(byCSS);
        checkElementIsVisibleByLocator(byXPath);
    }

    @AfterEach
    void stopDriver() {
        driver.quit();
    }
}
