import PageObject.CreateAccountLogInPage;
import PageObject.PersonalAccountPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest {

    SingletoneWebBrowser singletoneWebBrowser = SingletoneWebBrowser.getInstance();
    WebDriver driver;

    private static final String EXPECTED_TITLE = "Yandex.Mail — free, reliable email";

    @BeforeEach
    public void startDriver() {
        driver = singletoneWebBrowser.startBrowser();
    }

    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logInTest(String username, String password) {
        CreateAccountLogInPage createAccountLogInPage = new CreateAccountLogInPage(driver);
        PersonalAccountPage personalAccountPage = createAccountLogInPage
                .logInButton()
                .loginIntoPersonalAccount(username, password);
        assertEquals(username, personalAccountPage.getUserName());
    }

    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logOutTest(String username, String password) {
        CreateAccountLogInPage createAccountLogInPage = new CreateAccountLogInPage(driver);
        String actualTitle = createAccountLogInPage
                .logInButton()
                .loginIntoPersonalAccount(username, password)
                .logOut().getTitle();
        assertEquals(actualTitle, EXPECTED_TITLE);
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
