import base.BaseTest;
import dto.UserAccount;
import helpers.Helper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import store.Product;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private HomePage homepage;
    private Helper helper = new Helper();
    private final int createUserAccountIndex = 0;
    private final int logInUserAccountIndex = 1;

    @BeforeEach
    public void testPreparation() {
        homepage = new HomePage(driver);
    }

    @Test
    @DisplayName("Verify the ability to create an account")
    public void createAccountTest() {
        UserAccount userAccount = helper.getUserAccountByIndex(createUserAccountIndex);
        CreateAccountPage createAccountPage = homepage.createAccount(helper.getPrefixByDate() + userAccount.getEmail());
        createAccountPage.fillOutPersonalInfo(userAccount);
        createAccountPage.fillOutAddressInfo(userAccount);
        MyAccountPage myAccountPage = createAccountPage.clickRegisterButton();
        assertTrue(myAccountPage.getAccountName().contains(userAccount.getFirstName()));
    }

    @Test
    @DisplayName("Verify the ability to login in account")
    public void logInTest() {
        UserAccount userAccount = helper.getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        assertTrue(myAccountPage.getAccountName().contains(userAccount.getFirstName()));
    }

    @Test
    @DisplayName("Verify the ability to add to auto-created Wishlist")
    public void autoCreatedWishListTest() {
        UserAccount userAccount = helper.getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        MyWishListPage myWishListPage = myAccountPage.goToMyWishListPage();
        ProductPage productPage = myWishListPage
                .ensureWishListsIsEmpty()
                .goToProductDetailPage();
        myWishListPage = productPage
                .addProductToWishList()
                .goToMyAccountPage()
                .goToMyWishListPage();
        boolean actualResult = myWishListPage.checkProductWasAddedToWishList();
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Verify the ability to add to your Wishlist")
    public void addProductToYourWishListTest() {
        UserAccount userAccount = helper.getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        MyWishListPage myWishListPage = myAccountPage
                .goToMyWishListPage()
                .ensureWishListsIsEmpty()
                .createWishList();
        ProductPage productPage = myWishListPage
                .goToProductDetailPage()
                .addProductToWishList();
        myWishListPage = productPage
                .goToMyAccountPage()
                .goToMyWishListPage();
        boolean actualResult = myWishListPage.checkProductWasAddedToWishList();
        assertTrue(actualResult);
    }

    @Test
    @DisplayName("Verify the ability to add to cart")
    public void addProductToCartTest() {
        UserAccount userAccount = helper.getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        StorePage storePage = myAccountPage
                .goToStorePage()
                .switchListView();
        Product firstProduct = storePage.addRandomProductToCart();
        Product secondProduct = storePage.addRandomProductToCart();
        Product thirdProduct = storePage.addRandomProductToCart();
        CartPage cartPage = storePage.goToCartPage();
        assertTrue(cartPage.checkProduct(firstProduct));
        assertTrue(cartPage.checkProduct(secondProduct));
        assertTrue(cartPage.checkProduct(thirdProduct));
        assertTrue(cartPage.checkTotalPrice());
    }
}
