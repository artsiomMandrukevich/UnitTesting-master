import base.BaseTest;
import helpers.Helper;
import net.joshka.junit.json.params.JsonFileSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import pages.*;

import javax.json.JsonObject;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private HomePage homepage;
    private final Helper helper = new Helper();

    @BeforeEach
    public void testPreparartion() {
        homepage = new HomePage(driver);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset/testCreateAccount.json")
    @DisplayName("Verify the ability to create an account")
    public void createAccountTest(JsonObject jsonObject) {
        String firstName = jsonObject.getJsonObject("personalInfo").getString("firstName");
        String lastName = jsonObject.getJsonObject("personalInfo").getString("lastName");
        String email = helper.getPrefixByDate() + jsonObject.getJsonObject("personalInfo").getString("email");
        String password = jsonObject.getJsonObject("personalInfo").getString("password");
        String addressLine1 = jsonObject.getJsonObject("addressInfo").getString("addressLine1");
        String city = jsonObject.getJsonObject("addressInfo").getString("city");
        String state = jsonObject.getJsonObject("addressInfo").getString("state");
        String zipCode = jsonObject.getJsonObject("addressInfo").getString("zipCode");
        String mobilePhone = jsonObject.getJsonObject("addressInfo").getString("mobilePhone");
        String alias = jsonObject.getJsonObject("addressInfo").getString("alias");
        String accountNameExp = jsonObject.getJsonObject("expectedResult").getString("accountName");
        CreateAccountPage createAccountPage = homepage.fillOutEmailClickCreateAccountButton(email);
        createAccountPage.fillOutPersonalInfo(firstName, lastName, password);
        createAccountPage.fillOutAddressInfo(addressLine1, city, state, zipCode, mobilePhone, alias);
        MyAccountPage myAccountPage = createAccountPage.clickRegisterButton();
        assertEquals(accountNameExp, myAccountPage.getAccountName());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset/testAccount.json")
    @DisplayName("Verify the ability to login in account")
    public void logInTest(JsonObject jsonObject) {
        String email = jsonObject.getJsonObject("personalInfo").getString("email");
        String password = jsonObject.getJsonObject("personalInfo").getString("password");
        String accountNameExp = jsonObject.getJsonObject("expectedResult").getString("accountName");
        MyAccountPage myAccountPage = homepage.fillOutCredentialsClickSignInButton(email, password);
        assertEquals(accountNameExp, myAccountPage.getAccountName());
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset/testAccount.json")
    @DisplayName("Verify the ability to add to auto-created Wishlist")
    public void autoCreatedWishListTest(JsonObject jsonObject) {
        String email = jsonObject.getJsonObject("personalInfo").getString("email");
        String password = jsonObject.getJsonObject("personalInfo").getString("password");
        MyAccountPage myAccountPage = homepage.fillOutCredentialsClickSignInButton(email, password);
        boolean actualResult = myAccountPage
                .goToMyWishListPage()
                .ensureWishListsIsEmpty()
                .goToProductDetailPage()
                .addProductToWishList()
                .goToMyAccountPage()
                .goToMyWishListPage()
                .checkProductWasAddedToWishList();
        assertTrue(actualResult);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset/testAccount.json")
    @DisplayName("Verify the ability to add to your Wishlist")
    public void addProductToYourWishListTest(JsonObject jsonObject) {
        String email = jsonObject.getJsonObject("personalInfo").getString("email");
        String password = jsonObject.getJsonObject("personalInfo").getString("password");
        MyAccountPage myAccountPage = homepage.fillOutCredentialsClickSignInButton(email, password);
        boolean actualResult = myAccountPage
                .goToMyWishListPage()
                .ensureWishListsIsEmpty()
                .createWishList()
                .goToProductDetailPage()
                .addProductToWishList()
                .goToMyAccountPage()
                .goToMyWishListPage()
                .checkProductWasAddedToWishList();
        assertTrue(actualResult);
    }

    @ParameterizedTest
    @JsonFileSource(resources = "/dataset/testAccount.json")
    @DisplayName("Verify the ability to add to cart")
    public void addProductToCartTest(JsonObject jsonObject) {
        String email = jsonObject.getJsonObject("personalInfo").getString("email");
        String password = jsonObject.getJsonObject("personalInfo").getString("password");
        MyAccountPage myAccountPage = homepage.fillOutCredentialsClickSignInButton(email, password);
        boolean actualResult = myAccountPage
                .goToWomenCategoryPage()
                .addProductToCart()
                .goToCartPage()
                .comparePriceAndProductsInCart();
        assertTrue(actualResult);
    }
}
