package driver;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.jupiter.api.Assertions.fail;

public class BrowserCapabilities {

    public static MutableCapabilities getBrowserCapabilities(String browser) {
        switch(browser) {
            case "chrome":
                return getChromeCapabilities();
            case "firefox":
                return getFirefoxCapabilities();
            case "edge":
                return getEdgeCapabilities();
            default:
                fail("Please check your browser name");
        }
        return null;
    }

    private static MutableCapabilities getFirefoxCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
//      some firefox options
        return options;
    }

    private static MutableCapabilities getChromeCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("enable-automation");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return options;
    }

    public static MutableCapabilities getCloudCapabilities(String browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("os", "Windows");
        capabilities.setCapability("os_version", "10");
        capabilities.setCapability("browser", StringUtils.capitalize(browser));
        capabilities.setCapability("browser_version", "latest");
        return capabilities;
    }

    private static MutableCapabilities getEdgeCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
//      some edge options
        return capabilities;
    }
}
