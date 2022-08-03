package junittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static helpers.Const.*;
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
        testCart.deleteRealItem(realTestItem);
        testCart.deleteVirtualItem(virtualTestItem);
        assertEquals(0, testCart.getTotalPrice());
    }
}
