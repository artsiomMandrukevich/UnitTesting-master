package Helpers;

public class Const {

    // JsonParserTest
    public static final String PATH_TO_EUGEN_CART_JSON = "src/main/resources/eugen-cart.json";
    public static final String EXPECT_EUGEN_CART_NAME = "eugen-cart";
    public static final double EXPECT_EUGEN_TOTAL_PRICE = 26560.68;

    public static final String TEST_CART_NAME = "test-cart";
    public static final String EXPECT_FILE_RESULT = "{\"cartName\":\"test-cart\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

    // RealItemTest
    public static final double EXPECT_WEIGHT = 123.45;

    // VirtualItemTest
    public static final double EXPECT_SIZE_ON_DISK = 512;

    // CartTest
    public static final double PRICE = 10;
    public static final double EXPECT_TOTAL_PRICE = 36;

}
