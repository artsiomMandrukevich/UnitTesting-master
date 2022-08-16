import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest {
    private static WebDriver driver;

    private static final By LOGIN_BUTTON = By.linkText("Log in");
    private static final By USERNAME_INPUT = By.id("passp-field-login");
    private static final By USERNAME_SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By PASSWORD_INPUT = By.id("passp-field-passwd");
    private static final By PASSWORD_SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By USER_ACCOUNT_NAME = By.className("user-account__name");

    @BeforeEach
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logInTest(String username, String password) throws InterruptedException {
        driver.get("https://mail.yandex.com/");
        WebElement logInButton = driver.findElement(LOGIN_BUTTON);
        logInButton.click();
        WebElement logInInput = driver.findElement(USERNAME_INPUT);
        logInInput.sendKeys(username);
        WebElement signInButton = driver.findElement(USERNAME_SIGNIN_BUTTON);
        signInButton.click();
        // Thread.sleep is explicit waiter
        Thread.sleep(1000);
        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        passwordInput.sendKeys(password);
        WebElement passwordSignInButton = driver.findElement(PASSWORD_SIGNIN_BUTTON);
        passwordSignInButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(765)).until(ExpectedConditions
                .visibilityOfElementLocated(USER_ACCOUNT_NAME));
        WebElement userAccountName = driver.findElement(USER_ACCOUNT_NAME);
        assertEquals(username, userAccountName.getText());
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
