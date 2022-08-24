import base.BaseTest;
import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
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
    public void testPreparartion() {
        homepage = new HomePage(driver);
        logInPage = homepage.clicklogInButton();
    }

    @Feature("LOG IN GROUP")
    @AllureId("1")
    @DisplayName("Log In into personal area")
    @Description("This test checks the log in functionality.")
    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logInTest(String username, String password) {
        PersonalAccountPage personalAccountPage = logInPage
                .logIn(username, password);
        assertEquals(username, personalAccountPage.getUserName());
    }

    @Feature("LOG OUT GROUP")
    @AllureId("2")
    @DisplayName("Log Out from personal area")
    @Description("This test checks the log out functionality.")
    @ParameterizedTest
    @CsvSource({"testtestovt3stov, asdHygsad123", "testselenium202208, vMQGe25UrcksBtj"})
    public void logOutTest(String username, String password) {
        String actualTitle = logInPage
                .logIn(username, password)
                .logOut().getTitle();
        assertEquals(actualTitle, EXPECTED_TITLE);
    }
}
