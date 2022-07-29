package Helpers;

public class ConstTest {


    // JsonParserTest
    public static final String EXPECT_READ_FROM_FILE_CORRECT_FILE = "src/main/resources/eugen-cart.json";
    public static final String EXPECT_READ_FROM_FILE_INCORRECT_EXCEPTION_FILE = "src/main/resources/eugen-cart_exception.json";
    public static final String EXPECT_READ_FROM_FILE_NO_EXISTING_FILE = "src/main/resources/non_exististing_file";
    public static final String EXPECT_READ_FROM_FILE_CART_NAME = "eugen-cart";
    public static final double EXPECT_READ_FROM_FILE_TOTAL_PRICE = 26560.68;

    public static final String EXPECT_WRITE_CART_NAME = "write-test-cart";
    public static final String EXPECT_WRITE_PATH_FILE = "src/main/resources/" + EXPECT_WRITE_CART_NAME + ".json";

    // RealItemTest
    public static final double EXPECT_WEIGHT = 123.45;

    // VirtualItemTest
    public static final double EXPECT_SIZE_ON_DISK = 512;

    // CartTest
    public static final double EXPECT_PRICE = 10;


}
