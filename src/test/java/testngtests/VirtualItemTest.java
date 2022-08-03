package testngtests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import shop.VirtualItem;

import static helpers.Const.*;
import static org.testng.Assert.assertTrue;

public class VirtualItemTest {
    private VirtualItem virtualTestItem;

    @BeforeTest
    void prepareVirtualItemTestData() {
        virtualTestItem = new VirtualItem();
        virtualTestItem.setSizeOnDisk(EXPECT_SIZE_ON_DISK);
    }

    @Parameters({"expect-size-on-disk"})
    @Test
    void testToStringSizeOnDisk(String expectSizeOnDisk) {
        assertTrue(virtualTestItem.toString().contains("Size on disk: " + expectSizeOnDisk));
    }
}
