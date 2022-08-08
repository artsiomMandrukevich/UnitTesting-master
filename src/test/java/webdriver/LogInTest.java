package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest {

    private WebDriver driver;
    private static final String USERNAME = "testselenium202208";
    private static final String PASSWORD = "vMQGe25UrcksBtj";
    private static final By LOGIN_BUTTON = By.linkText("Log in");
    private static final By USERNAME_INPUT = By.id("passp-field-login");
    private static final By USERNAME_SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By PASSWORD_INPUT = By.id("passp-field-passwd");
    private static final By PASSWORD_SIGNIN_BUTTON = By.xpath("//button[@id='passp:sign-in']");
    private static final By USER_ACCOUNT_NAME = By.className("user-account__name");

    @BeforeEach
    void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    void logInTest() {
        driver.get("https://mail.yandex.com/");

        WebElement logInButton = driver.findElement(LOGIN_BUTTON);
        logInButton.click();

        WebElement userNameInput = driver.findElement(USERNAME_INPUT);
        userNameInput.sendKeys(USERNAME);

        WebElement userNameSignInButton = driver.findElement(USERNAME_SIGNIN_BUTTON);
        userNameSignInButton.click();

        WebElement passwordInput = driver.findElement(PASSWORD_INPUT);
        passwordInput.sendKeys(PASSWORD);

        WebElement passwordSignInButton = driver.findElement(PASSWORD_SIGNIN_BUTTON);
        passwordSignInButton.click();

        // I especially left the explicit waiting because the implicit waiting is not enough to test my asserting
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(USER_ACCOUNT_NAME));
        WebElement userAccountName = driver.findElement(USER_ACCOUNT_NAME);
        assertEquals(USERNAME, userAccountName.getText());
    }

    @AfterEach
    void stopDriver() {
        driver.quit();
    }
}
