package testngtests;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static helpers.Const.*;
import static helpers.Const.EUGEN_TOTAL_PRICE;
import static org.testng.Assert.assertEquals;


public class JsonParserTest {
    private Parser parser;
    private Cart testCart;

    @BeforeGroups("test-group")
    void prepareParserAndTestCart() {
        testCart = new Cart(TEST_CART_NAME);
        parser = new JsonParser();
    }

    @Test(groups = {"test-group", "disable"})
    void testReadFromFile() {
        SoftAssert softAssert = new SoftAssert();
        Cart readCartTest = parser.readFromFile(new File(PATH_TO_EUGEN_CART_JSON));
        softAssert.assertEquals(readCartTest.getCartName(), EUGEN_CART_NAME, "Cart's names do not match");
        softAssert.assertEquals(readCartTest.getTotalPrice(), EUGEN_TOTAL_PRICE, "Total prices do not match");
        softAssert.assertAll();
    }

    @DataProvider(name = "testData")
    public static Object[][] prepareFileName() {
        return new Object[][]{{"test.js"}, {"empty.json"}, {""}};
    }

    @Test(groups = {"test-group"}, dataProvider = "testData", expectedExceptions = NoSuchFileException.class)
    void testReadFromFileException(String fileName) {
        parser.readFromFile(new File("src/main/resources/" + fileName + ".json"));
    }

    @Test(groups = {"test-group"})
    void testWriteToFile() throws IOException {
        parser.writeToFile(testCart);
        String actualFileResult = Files.readString(Path.of("src/main/resources/" + TEST_CART_NAME + ".json"));
        assertEquals(actualFileResult, EXPECT_FILE_RESULT, "Files do not match");
    }
}
