package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shop.VirtualItem;
import Helpers.ConstTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualItemTest {

    private VirtualItem virtualItemTest;

    @BeforeEach
    void prepareVirtualItemTestData(){
        virtualItemTest = new VirtualItem();
        virtualItemTest.setSizeOnDisk(ConstTest.EXPECT_SIZE_ON_DISK);
    }

    @Test()
    void getSizeOnDiskTest(){
        assertEquals(virtualItemTest.getSizeOnDisk(), ConstTest.EXPECT_SIZE_ON_DISK);
    }

}
