import helpers.Element;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RefreshPage {
    Element element = new Element();

    private static WebDriver driver;

    private static final By DOWNLOAD_BUTTON = By.id("cricle-btn");
    private static final By PERCENT_TEXT = By.cssSelector(".percenttext");

    @Before
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void waitForUser() {
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

        element.clickButton(driver, DOWNLOAD_BUTTON);

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until((ExpectedCondition<Boolean>) d -> d != null &&
                        d.findElement(PERCENT_TEXT).getText().contains("50"));

        driver.navigate().refresh();
    }

    @After
    public void stopDriver() {
        driver.quit();
    }
}
