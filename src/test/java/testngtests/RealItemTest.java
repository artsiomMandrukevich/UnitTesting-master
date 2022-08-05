package testngtests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import shop.RealItem;

import static helpers.Const.EXPECT_WEIGHT;
import static org.testng.Assert.assertTrue;

public class RealItemTest {
    private RealItem realTestItem;

    @BeforeTest
    void prepareRealItemTestData() {
        realTestItem = new RealItem();
        realTestItem.setWeight(EXPECT_WEIGHT);
    }

    @Test
    void testToStringWeight() {
        assertTrue(realTestItem.toString().contains("Weight: " + EXPECT_WEIGHT));
    }
}
