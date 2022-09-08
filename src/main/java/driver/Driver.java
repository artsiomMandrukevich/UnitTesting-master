package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.api.internal.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class Driver {

    private static WebDriver driver;
    private static final String browser = System.getProperty("browser", "chrome");
    private static final String remote = System.getProperty("remote");

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static WebDriver createDriver() {
        MutableCapabilities capabilities = BrowserCapabilities.getBrowserCapabilities(browser);
        if (!StringUtils.isNullOrEmpty(remote)) {
            try {
                return new RemoteWebDriver(new URL(remote), BrowserCapabilities.getCloudCapabilities(browser));
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("Invalid 'remote' parameter: " + remote, e);
            }
        }
        return getLocalDriver(capabilities);
    }

    private static WebDriver getLocalDriver(Capabilities capabilities) {
        switch (browser) {
            case "chrome" -> {
                WebDriverManager.getInstance(ChromeDriver.class).setup();
                return new ChromeDriver((ChromeOptions) Objects.requireNonNull(capabilities));
            }
            case "firefox" -> {
                WebDriverManager.getInstance(FirefoxDriver.class).setup();
                return new FirefoxDriver((FirefoxOptions) Objects.requireNonNull(capabilities));
            }
            case "edge" -> {
                WebDriverManager.getInstance(EdgeDriver.class).setup();
                return new EdgeDriver((EdgeOptions) Objects.requireNonNull(capabilities));
            }
            default -> {
                fail("Please check your browser name");
            }
        }
        return null;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
