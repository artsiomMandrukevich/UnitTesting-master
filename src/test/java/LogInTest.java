import helpers.Element;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.*;
import java.util.*;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

@RunWith(value = Parameterized.class)
public class LogInTest {
    Element element = new Element();

    private static WebDriver driver;
    private final String username;
    private final String password;

    private static final By LOGIN_BUTTON = By.linkText("Log in");
    private static final By USERNAME_INPUT = By.id("passp-field-login");
    private static final By USERNAME_SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By PASSWORD_INPUT = By.id("passp-field-passwd");
    private static final By PASSWORD_SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By USER_ACCOUNT_NAME = By.className("user-account__name");

    public LogInTest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Parameters
    public static Collection testData() throws IOException {
        return getTestData("src/test/java/resources/data.csv");
    }

    public static Collection<String[]> getTestData(String fileName)
            throws IOException {
        List<String[]> records = new ArrayList<>();
        String record;
        BufferedReader file = new BufferedReader(new
                FileReader(fileName));
        while ((record = file.readLine()) != null) {
            String[] fields = record.split(",");
            records.add(fields);
        }
        file.close();
        return records;
    }

    @Before
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void logInTest() throws InterruptedException {
        driver.get("https://mail.yandex.com/");

        element.clickButton(driver, LOGIN_BUTTON);

        element.sendKeys(driver, USERNAME_INPUT, username);

        element.clickButton(driver, USERNAME_SIGNIN_BUTTON);

        // Thread.sleep is explicit waiter
        Thread.sleep(1000);

        element.sendKeys(driver, PASSWORD_INPUT, password);

        element.clickButton(driver, PASSWORD_SIGNIN_BUTTON);

        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1)).until(ExpectedConditions
                .visibilityOfElementLocated(USER_ACCOUNT_NAME));
        WebElement userAccountName = driver.findElement(USER_ACCOUNT_NAME);

        assertEquals(username, userAccountName.getText());
    }

    @After
    public void stopDriver() {
        driver.quit();
    }
}
