import pages.HomePage;
import pages.LogInPage;
import pages.PersonalAccountPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest extends BaseTest {

    private HomePage homepage;
    private LogInPage logInPage;

    private static final String EXPECTED_TITLE = "Yandex.Mail — free, reliable email";

    @BeforeEach
    public void startDriver() {
        homepage = new HomePage(driver);
        logInPage = homepage.clicklogInButton();
    }

    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logInTest(String username, String password) {
        PersonalAccountPage personalAccountPage = logInPage
                .loginToPersonalArea(username, password);
        assertEquals(username, personalAccountPage.getUserName());
    }

    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logOutTest(String username, String password) {
        String actualTitle = logInPage
                .loginToPersonalArea(username, password)
                .logOut().getTitle();
        assertEquals(actualTitle, EXPECTED_TITLE);
    }
}
