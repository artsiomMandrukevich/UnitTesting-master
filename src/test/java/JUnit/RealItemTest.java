package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.RealItem;
import Helpers.ConstTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RealItemTest {

    private RealItem realItemTest;

    @BeforeEach
    void prepareRealItemTestData(){
        realItemTest = new RealItem();
        realItemTest.setWeight(ConstTest.EXPECT_WEIGHT);
    }

    @Test()
    void getWeightTest(){
        assertEquals(realItemTest.getWeight(), ConstTest.EXPECT_WEIGHT);
    }
}
