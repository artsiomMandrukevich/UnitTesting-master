package Bottlenecks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class FirefoxSetDownload {

    public WebDriver driver;

    @Test
    public void setDownloadFirefox() throws InterruptedException {
        FirefoxProfile profile = new FirefoxProfile();
        // set default download place
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "c:\\Temp\\");
        // set enable auto-download in firefox
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/zip, application/x-zip, application/x-zip-compressed");

        // need to transfer FireFoxOptions into driver instead of FireFoxProfile
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);

        WebDriverManager.getInstance(FirefoxDriver.class).setup();
        driver = new FirefoxDriver(firefoxOptions);

        driver.get("http://the-internet.herokuapp.com/download");
        driver.findElement(By.cssSelector(".example a:nth-of-type(1)")).click();
        Thread.sleep(5000);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
