package driver;

import helpers.PropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static driver.Browsers.*;
import static driver.Servers.*;

public class Driver {

    private static WebDriver driver;
    private static final PropertiesReader propertiesReader = new PropertiesReader();
    private static final String browser = System.getProperty("browser", "chrome").toUpperCase();
    private static final String server = System.getProperty("server", "local").toUpperCase();
    private static final String urlGrid = System.getProperty("url_grid", "http://localhost:4444");

    private Driver() {
    }

    // If browser name not in (chrome, firefox) - use chrome.
    private static String getBrowserName() {
        String browserName;
        if (Arrays.toString(Browsers.values()).contains(browser)) {
            browserName = browser;
        } else {
            System.out.println(browser + " is incorrect browser. Test will be run in chrome.");
            browserName = CHROME.toString();
        }
        return browserName;
    }

    // If server name not in (local, grid, saucelab) - use local machine.
    private static String getServerName() {
        String serverName;
        if (Arrays.toString(Servers.values()).contains(server)) {
            serverName = server;
        } else {
            System.out.println(server + " is incorrect server name. Test will be run on local machine.");
            serverName = LOCAL.toString();
        }
        return serverName;
    }

    private static DesiredCapabilities setBrowserNameCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(getBrowserName().toLowerCase());
        return capabilities;
    }

    private static ChromeOptions setChromeOptions() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "Windows 10, Chrome latest");
        sauceOptions.put("name", "Final test");
        browserOptions.setCapability("sauce:options", sauceOptions);
        return browserOptions;
    }

    private static FirefoxOptions setFireFoxOptions() {
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("build", "Windows 10, FireFox latest");
        sauceOptions.put("name", "Final test");
        browserOptions.setCapability("sauce:options", sauceOptions);
        return browserOptions;
    }

    private static WebDriver getDriverForLocalRun() {
        if (getBrowserName().equals(FIREFOX.toString())) {
            WebDriverManager.getInstance(FirefoxDriver.class).setup();
            return new FirefoxDriver();
        }
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        return new ChromeDriver();
    }

    private static WebDriver gerDriverForGridRun() {
        try {
            driver = new RemoteWebDriver(new URL(urlGrid), setBrowserNameCapabilities());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }

    private static WebDriver getDriverForSauceLabRun() {
        String sauceLabUrl = propertiesReader.getPropertyByName("url.saucelab");
        URL url;
        try {
            url = new URL(sauceLabUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (getBrowserName().equals(FIREFOX.toString())) {
            driver = new RemoteWebDriver(url, setFireFoxOptions());
        } else {
            driver = new RemoteWebDriver(url, setChromeOptions());
        }
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (getServerName()) {
                case "LOCAL" -> driver = getDriverForLocalRun();
                case "GRID" -> driver = gerDriverForGridRun();
                case "SAUCELAB" -> driver = getDriverForSauceLabRun();
            }
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
