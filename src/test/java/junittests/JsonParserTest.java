package junittests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;

import static helpers.Const.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {
    private Parser parser;
    private Cart testCart;

    @BeforeEach
    void prepareParserAndTestCart() {
        testCart = new Cart(TEST_CART_NAME);
        parser = new JsonParser();
    }

    @Test
    void testReadFromFile() {
        Cart readCartTest = parser.readFromFile(new File(PATH_TO_EUGEN_CART_JSON));
        assertAll(
                "readFromFileTest",
                () -> assertEquals(EUGEN_CART_NAME, readCartTest.getCartName()),
                () -> assertEquals(EUGEN_TOTAL_PRICE, readCartTest.getTotalPrice())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"testname", "fail_name", ""})
    void testReadFromFileException(String fileName) {
        assertThrows(NoSuchFileException.class, () -> parser.readFromFile(new File("src/main/resources/" + fileName + ".json")));
    }

    @Test
    void testWriteToFile() throws IOException {
        parser.writeToFile(testCart);
        String actualFileResult = Files.readString(Path.of("src/main/resources/" + TEST_CART_NAME + ".json"));
        assertEquals(EXPECT_FILE_RESULT, actualFileResult);
    }
}
