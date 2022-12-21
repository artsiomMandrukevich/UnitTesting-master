package base;

import driver.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

@ExtendWith(AllureTestWatcher.class)
public class BaseTest {

    protected WebDriver driver;
    protected static byte[] screenshot;
    protected static String browserInfo;

    @BeforeEach
    public void startBrowser() {
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @AfterEach
    public void stopDriver() {
        screenshot = makeScreenshot();
        browserInfo = getBrowserInformation();
        Driver.tearDown();
    }

    private byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private String getBrowserInformation() {
        Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = caps.getBrowserName();
        String browserVersion = caps.getBrowserVersion();
        return "browser: " + browserName + ", browserversion: " + browserVersion;
    }
}
