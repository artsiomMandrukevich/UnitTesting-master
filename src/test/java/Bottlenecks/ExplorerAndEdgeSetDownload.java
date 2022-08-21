package Bottlenecks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.HashMap;

public class ExplorerAndEdgeSetDownload {

    public WebDriver driver;

    @Test
    public void setDownloadEdge() throws InterruptedException {

        HashMap<String, Object> edgePrefs= new HashMap<>();
        edgePrefs.put("download.default_directory", "c:\\Temp\\");
        EdgeOptions options = new EdgeOptions();
        options.setExperimentalOption("prefs", edgePrefs);

        WebDriverManager.getInstance(EdgeDriver.class).setup();
        driver = new EdgeDriver(options);

        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a:nth-of-type(1)")).click();
        Thread.sleep(5000);
    }

    @Test
    public void setDownloadInternetExplorer() throws InterruptedException {

        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability("ie.ensureCleanSession", true);
        options.setCapability("ignoreProtectedModeSettings", true);
//        options.setCapability("ie.forceCreateProcessApi", true);
//        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        options.disableNativeEvents();
        options.ignoreZoomSettings();

        WebDriverManager.getInstance(InternetExplorerDriver.class).setup();
        driver = new InternetExplorerDriver(options);

        Thread.sleep(5000);
        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a:nth-of-type(1)")).click();

    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}