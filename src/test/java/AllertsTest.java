import helpers.Element;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static junit.framework.TestCase.assertEquals;

public class AllertsTest {
    Element element = new Element();

    private static WebDriver driver;

    private static final By ALERT_BOX_BUTTON = By.cssSelector("button[class='btn btn-default']");
    private static final By CONFIRM_BOX_BUTTON = By.cssSelector("button[class='btn btn-default btn-lg']");
    private static final By ALERT_PROMT_BOX_BUTTON = By.xpath("//button[text()='Click for Prompt Box']");
    private static final By ACTUAL_MESSAGE = By.id("confirm-demo");
    private static final By ACTUAL_PROMPT_MESSAGE = By.id("prompt-demo");

    @Before
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
    }

    @Test
    public void alertBox() {
        element.clickButton(driver, ALERT_BOX_BUTTON);

        Alert alert = driver.switchTo().alert();
        assertEquals("I am an alert box!", alert.getText());
        alert.accept();
    }

    @Test
    public void confirmBoxAccept() {
        element.clickButton(driver, CONFIRM_BOX_BUTTON);

        Alert alert = driver.switchTo().alert();
        assertEquals("Press a button!", alert.getText());
        alert.accept();

        assertEquals("You pressed OK!", driver.findElement(ACTUAL_MESSAGE).getText());
    }

    @Test
    public void confirmBoxDissmiss() {
        element.clickButton(driver, CONFIRM_BOX_BUTTON);

        Alert alert = driver.switchTo().alert();
        assertEquals("Press a button!", alert.getText());
        alert.dismiss();

        assertEquals("You pressed Cancel!", driver.findElement(ACTUAL_MESSAGE).getText());
    }

    @Test
    public void alertPromtBox() {
        element.clickButton(driver, ALERT_PROMT_BOX_BUTTON);

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Test message");
        alert.accept();

        assertEquals("You have entered 'Test message' !", driver.findElement(ACTUAL_PROMPT_MESSAGE).getText());
    }

    @After
    public void stopDriver() {
        driver.quit();
    }
}
