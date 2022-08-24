package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver()  {
        if (driver == null) {
////            Windows 8.1, Mozilla Firefox 100.0
////            !!! The Saucelabs is not supported firefox version 39, I used firefox version = 100
//
//            FirefoxOptions browserOptions = new FirefoxOptions();
//            browserOptions.setPlatformName("Windows 8.1");
//            browserOptions.setBrowserVersion("100");
//            Map<String, Object> sauceOptions = new HashMap<>();
//            sauceOptions.put("build", "Windows 8.1, Mozilla Firefox");
//            sauceOptions.put("name", "Log out from personal are");
//            browserOptions.setCapability("sauce:options", sauceOptions);

//            //            Windows 10, Edge latest version
//            EdgeOptions browserOptions = new EdgeOptions();
//            browserOptions.setPlatformName("Windows 10");
//            browserOptions.setBrowserVersion("latest");
//            Map<String, Object> sauceOptions = new HashMap<>();
//            sauceOptions.put("build", "Windows 10, Edge latest version");
//            sauceOptions.put("name", "Log out from personal area");
//            browserOptions.setCapability("sauce:options", sauceOptions);

//            //            Linux 10, Edge latest version
//            !!! The Saucelabs is not supported firefox version 40, I used firefox version = 102

            ChromeOptions browserOptions = new ChromeOptions();
            browserOptions.setPlatformName("macOS 10.15");
            browserOptions.setBrowserVersion("102");
            Map<String, Object> sauceOptions = new HashMap<>();
            sauceOptions.put("build", "Linux, Chrome 102");
            sauceOptions.put("name", "Log out from personal area");
            browserOptions.setCapability("sauce:options", sauceOptions);

            URL url = null ;
            try {
                url = new URL("https://oauth-e2etestqawolf-48c80:d092c5b8-d6db-454f-a185-45bb9a055650@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver = new RemoteWebDriver(url, browserOptions);

        }
        return driver;
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
