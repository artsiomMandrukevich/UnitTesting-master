import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllertsTest {
    private static WebDriver driver;

    private static final By ALERT_BOX_BUTTON = By.cssSelector("button[class='btn btn-default']");
    private static final By CONFIRM_BOX_BUTTON = By.cssSelector("button[class='btn btn-default btn-lg']");
    private static final By ALERT_PROMT_BOX_BUTTON = By.xpath("//button[text()='Click for Prompt Box']");
    private static final By ACTUAL_MESSAGE = By.id("confirm-demo");
    private static final By ACTUAL_PROMPT_MESSAGE = By.id("prompt-demo");

    @BeforeEach
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
    }

    @Test
    public void alertBoxTest() {
        WebElement webElement = driver.findElement(ALERT_BOX_BUTTON);
        webElement.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("I am an alert box!", alert.getText());
    }

    @Test
    public void confirmBoxAcceptTest() {
        WebElement webElement = driver.findElement(CONFIRM_BOX_BUTTON);
        webElement.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Press a button!", alert.getText());
        alert.accept();
        assertEquals("You pressed OK!", driver.findElement(ACTUAL_MESSAGE).getText());
    }

    @Test
    public void confirmBoxDissmissTest() {
        WebElement webElement = driver.findElement(CONFIRM_BOX_BUTTON);
        webElement.click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Press a button!", alert.getText());
        alert.dismiss();
        assertEquals("You pressed Cancel!", driver.findElement(ACTUAL_MESSAGE).getText());
    }

    @Test
    public void alertPromtBoxTest() {
        WebElement webElement = driver.findElement(ALERT_PROMT_BOX_BUTTON);
        webElement.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Test message");
        alert.accept();
        assertEquals("You have entered 'Test message' !", driver.findElement(ACTUAL_PROMPT_MESSAGE).getText());
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
