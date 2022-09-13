import base.BaseTest;
import dto.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import store.Product;


import static helpers.Helper.getPrefixByDate;
import static helpers.Helper.getUserAccountByIndex;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartTest extends BaseTest {

    private HomePage homepage;
    private final int createUserAccountIndex = 0;
    private final int logInUserAccountIndex = 1;

    @BeforeEach
    public void testPreparation() {
        homepage = new HomePage(driver);
    }

    @Test
    @DisplayName("Verify the ability to create an account")
    public void createAccountTest() {
        UserAccount userAccount = getUserAccountByIndex(createUserAccountIndex);
        CreateAccountPage createAccountPage = homepage.createAccount(getPrefixByDate() + userAccount.getEmail());
        createAccountPage.fillPersonalInfo(userAccount);
        createAccountPage.fillAddressInfo(userAccount);
        MyAccountPage myAccountPage = createAccountPage.clickRegister();
        assertTrue(myAccountPage.getAccountName().contains(userAccount.getFirstName()));
    }

    @Test
    @DisplayName("Verify the ability to login in account")
    public void logInTest() {
        UserAccount userAccount = getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        assertTrue(myAccountPage.getAccountName().contains(userAccount.getFirstName()));
    }

    @Test
    @DisplayName("Verify the ability to add to auto-created Wishlist")
    public void autoCreatedWishListTest() {
        UserAccount userAccount = getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        MyWishListPage myWishListPage = myAccountPage.goToMyWishListPage();
        ProductPage productPage = myWishListPage
                .ensureWishListsIsEmpty()
                .goToProductDetailPage();
        myWishListPage = productPage
                .addProductToWishList()
                .goToMyAccountPage()
                .goToMyWishListPage();
        assertTrue(myWishListPage.checkProductWasAddedToWishList());
    }

    @Test
    @DisplayName("Verify the ability to add to your Wishlist")
    public void addProductToYourWishListTest() {
        UserAccount userAccount = getUserAccountByIndex(logInUserAccountIndex);
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
        assertTrue(myWishListPage.checkProductWasAddedToWishList());
    }

    @Test
    @DisplayName("Verify the ability to add to cart")
    public void addProductToCartTest() {
        UserAccount userAccount = getUserAccountByIndex(logInUserAccountIndex);
        MyAccountPage myAccountPage = homepage.logIn(userAccount);
        StorePage storePage = myAccountPage
                .goToStorePage()
                .switchListView();
        Product firstProduct = storePage.addRandomProductToCart();
        Product secondProduct = storePage.addRandomProductToCart();
        Product thirdProduct = storePage.addRandomProductToCart();
        CartPage cartPage = storePage.goToCartPage();
        assertTrue(cartPage.checkProductPrice(firstProduct));
        assertTrue(cartPage.checkProductPrice(secondProduct));
        assertTrue(cartPage.checkProductPrice(thirdProduct));
        assertTrue(cartPage.checkTotalCalculation());
    }
}
