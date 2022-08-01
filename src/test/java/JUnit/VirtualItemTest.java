package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;
import static Helpers.Const.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VirtualItemTest {

    private VirtualItem virtualTestItem;

    @BeforeEach
    void prepareVirtualItemTestData() {
        virtualTestItem = new VirtualItem();
        virtualTestItem.setSizeOnDisk(EXPECT_SIZE_ON_DISK);
    }

    @Test
    void testToStringSizeOnDisk() {
        assertTrue(virtualTestItem.toString().contains("Size on disk: " + EXPECT_SIZE_ON_DISK));
    }

}
