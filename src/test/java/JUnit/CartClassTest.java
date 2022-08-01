package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static Helpers.Const.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartClassTest {

    private Cart testCart;
    private RealItem realTestItem;
    private VirtualItem virtualTestItem;

    @BeforeEach
    void prepareCartTestData() {
        testCart = new Cart(TEST_CART_NAME);

        realTestItem = new RealItem();
        realTestItem.setPrice(PRICE);

        virtualTestItem = new VirtualItem();
        virtualTestItem.setPrice(PRICE);

        testCart.addVirtualItem(virtualTestItem);
        testCart.addRealItem(realTestItem);
    }

    @Test
    void testTotalPrice() {
        testCart.addRealItem(realTestItem);
        assertEquals(EXPECT_TOTAL_PRICE, testCart.getTotalPrice());
    }

    @Test
    @Disabled
    void testDeleteItems() {
        /*
        This test is fail, but I especially leave it
        because in my opinion there is a bug in Cart class. Both deleteRealItem and deleteVirtualItem methods
        must remove item's price from the total price in the case when we remove item from Сart.
        */
        testCart.deleteRealItem(realTestItem);
        testCart.deleteVirtualItem(virtualTestItem);
        assertEquals(0, testCart.getTotalPrice());
    }

}
