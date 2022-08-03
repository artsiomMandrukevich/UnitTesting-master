package testngtests;

import org.testng.annotations.*;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static helpers.Const.*;
import static org.testng.Assert.assertEquals;

public class CartTest {
    private Cart testCart;
    private RealItem realTestItem;
    private VirtualItem virtualTestItem;

    @BeforeGroups("test-group")
    void prepareCartTestData() {
        testCart = new Cart(TEST_CART_NAME);

        realTestItem = new RealItem();
        realTestItem.setPrice(PRICE);

        virtualTestItem = new VirtualItem();
        virtualTestItem.setPrice(PRICE);

        testCart.addVirtualItem(virtualTestItem);
        testCart.addRealItem(realTestItem);
    }

    @Test(groups = "test-group")
    void testTotalPrice() {
        testCart.addRealItem(realTestItem);
        assertEquals(testCart.getTotalPrice(), EXPECT_TOTAL_PRICE, "Total prices after adding items do not match");
    }

    @Test(groups = "test-group", enabled = false)
    void testDeleteItems() {
        testCart.deleteRealItem(realTestItem);
        testCart.deleteVirtualItem(virtualTestItem);
        assertEquals(testCart.getTotalPrice(), 0, "Total prices after deleting items do not match");
    }
}
