package Bottlenecks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeSetDownload {

    public WebDriver driver;

    @Test
    public void setDownloadChrome() throws InterruptedException {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", "c:\\Temp\\");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");

        // need to transfer ChromeOptions into driver instead of capabilities
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);

        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver(options);

        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a:nth-of-type(1)")).click();
        Thread.sleep(5000);

    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

}
