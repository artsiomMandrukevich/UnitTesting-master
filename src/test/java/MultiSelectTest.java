import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class MultiSelectTest {
    private static WebDriver driver;

    private static final By MULTI_SELECT = By.name("States");
    private final List<String> EXP_SELECTED_OPTIONS = Arrays.asList("California", "Ohio", "Pennsylvania");
    private final List<String> ACT_EXPECTED_OPTIONS = new ArrayList<>();

    @Before
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void multiSelect() {
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");

        Select multiSelect = new Select(driver.findElement(MULTI_SELECT));

        for (String selectOption : EXP_SELECTED_OPTIONS) {
            multiSelect.selectByVisibleText(selectOption);
        }

        for (WebElement option : multiSelect.getAllSelectedOptions()) {
            ACT_EXPECTED_OPTIONS.add(option.getText());
        }

        assertArrayEquals(EXP_SELECTED_OPTIONS.toArray(), ACT_EXPECTED_OPTIONS.toArray());
    }

    @After
    public void stopDriver() {
        driver.quit();
    }
}