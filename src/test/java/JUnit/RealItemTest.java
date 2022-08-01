package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.RealItem;

import static Helpers.Const.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RealItemTest {

    private RealItem realTestItem;

    @BeforeEach
    void prepareRealItemTestData() {
        realTestItem = new RealItem();
        realTestItem.setWeight(EXPECT_WEIGHT);
    }

    @Test
    void testToStringWeight() {
        assertTrue(realTestItem.toString().contains("Weight: " + EXPECT_WEIGHT));
    }

}
