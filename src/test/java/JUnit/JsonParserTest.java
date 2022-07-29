package JUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import Helpers.ConstTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {

    private Parser parser;
    private Cart writeCartTest;
    private Cart readFromWriteCartTest;


    private void prepareWriteToFileTest(){
        writeCartTest = new Cart(ConstTest.EXPECT_WRITE_CART_NAME);
        parser.writeToFile(writeCartTest);
        readFromWriteCartTest = parser.readFromFile(new File(ConstTest.EXPECT_WRITE_PATH_FILE));
    }

    @BeforeEach
    void prepareParser(){
        parser = new JsonParser();
    }

    @Test
    void readFromFileTest(){
        Cart readCartTest = parser.readFromFile(new File(ConstTest.EXPECT_READ_FROM_FILE_CORRECT_FILE));
        assertAll(
                "readFromFileTest",
                () -> assertEquals(readCartTest.getCartName(), ConstTest.EXPECT_READ_FROM_FILE_CART_NAME),
                () -> assertEquals(readCartTest.getTotalPrice(), ConstTest.EXPECT_READ_FROM_FILE_TOTAL_PRICE)
        );
    }

    @Test()
    void readFromFileExceptionTest(){
        assertThrows(Exception.class, ()->parser.readFromFile(new File(ConstTest.EXPECT_READ_FROM_FILE_INCORRECT_EXCEPTION_FILE)));
    }

    @Test()
    void readFromNoExistingFileTest(){
        try{
            parser.readFromFile(new File(ConstTest.EXPECT_READ_FROM_FILE_NO_EXISTING_FILE));
        } catch (RuntimeException e) {
            assertEquals("File " + ConstTest.EXPECT_READ_FROM_FILE_NO_EXISTING_FILE.replace("/", "\\") + ".json not found!",
                    e.getMessage());
        }
    }

    @Test()
    @Disabled()
    void writeToFileTest(){
        prepareWriteToFileTest();
        assertEquals(readFromWriteCartTest.getCartName(), ConstTest.EXPECT_WRITE_CART_NAME);
    }

}
