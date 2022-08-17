import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SingletoneWebBrowser {

    private static SingletoneWebBrowser instanceOfSingletoneWebBrowser = null;
    private WebDriver driver;

    private SingletoneWebBrowser() {
    }

    public WebDriver startBrowser() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://mail.yandex.com/");
        return driver;
    }

    public static SingletoneWebBrowser getInstance() {
        if (instanceOfSingletoneWebBrowser == null) {
            instanceOfSingletoneWebBrowser = new SingletoneWebBrowser();
        }
        return instanceOfSingletoneWebBrowser;
    }
}
