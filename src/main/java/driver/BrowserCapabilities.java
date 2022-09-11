package driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class BrowserCapabilities {

    public static MutableCapabilities getBrowserCapabilities(String browser) {
        switch (browser) {
            case "chrome":
                return getChromeCapabilities();
            case "firefox":
                return getFirefoxCapabilities();
            default:
                fail("getBrowserCapabilities: Please check your browser name");
        }
        return null;
    }

    private static MutableCapabilities getFirefoxCapabilities() {
        return new FirefoxOptions();
    }

    private static MutableCapabilities getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("enable-automation");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }

    public static MutableCapabilities getCloudCapabilities(String browser, String remote) {
        MutableCapabilities capabilities = getBrowserCapabilities(browser);
        Objects.requireNonNull(capabilities).setCapability("platformName", "Windows 11");
        if (remote.contains("saucelabs.com")) {
            capabilities.setCapability("browserVersion", "latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "Final task build");
            sauceOptions.put("name", "Final test");
            capabilities.setCapability("sauce:options", sauceOptions);
        }
        return capabilities;
    }
}
