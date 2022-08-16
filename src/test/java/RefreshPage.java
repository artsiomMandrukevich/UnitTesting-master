import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RefreshPage {
    private static WebDriver driver;

    private static final By DOWNLOAD_BUTTON = By.id("cricle-btn");
    private static final By PERCENT_TEXT = By.cssSelector(".percenttext");

    @BeforeEach
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void refreshPageWhenPercentIsEnough() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
        WebElement webElement = driver.findElement(DOWNLOAD_BUTTON);
        webElement.click();
        new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(50))
                .until(driver -> driver.findElement(PERCENT_TEXT).getText().contains("50"));
        driver.navigate().refresh();
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
