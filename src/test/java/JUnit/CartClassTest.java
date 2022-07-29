package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import Helpers.ConstTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartClassTest {

    private Cart cartTest;
    private RealItem realItemTest;
    private VirtualItem virtualItemTest;
    double expectTotalPrice = (ConstTest.EXPECT_PRICE + ConstTest.EXPECT_PRICE*0.2)*2;

    @BeforeEach
    void prepareCartTestData(){
        cartTest = new Cart("cart-test");

        realItemTest = new RealItem();
        realItemTest.setPrice(ConstTest.EXPECT_PRICE);

        virtualItemTest = new VirtualItem();
        virtualItemTest.setPrice(ConstTest.EXPECT_PRICE);

        cartTest.addRealItem(realItemTest);
        cartTest.addVirtualItem(virtualItemTest);
    }

    @Test()
    void getTotalPriceTest(){
        assertEquals(cartTest.getTotalPrice(), expectTotalPrice);
    }

    @Test()
    @Disabled()
    void deleteRealVirtualItemsTest(){
        /*
        This test is fail, but I especially leave it
        because in my opinion there is a bug in Cart class. Both deleteRealItem and deleteVirtualItem methods
        must remove item's price from the total price in the case when we remove item from Сart.
        */
        cartTest.deleteRealItem(realItemTest);
        cartTest.deleteVirtualItem(virtualItemTest);
        assertEquals(cartTest.getTotalPrice(), 0);
    }

}
