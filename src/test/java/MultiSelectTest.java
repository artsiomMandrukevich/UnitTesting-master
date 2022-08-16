import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiSelectTest {
    private static WebDriver driver;

    private static final By MULTI_SELECT = By.name("States");

    @BeforeEach
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void multiSelectTest() {
        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        Select multiSelect = new Select(driver.findElement(MULTI_SELECT));
        List<String> expectedStates = new Random().ints(3, 0, multiSelect.getOptions().size()).boxed()
                .map(i -> multiSelect.getOptions().get(i).getText()).collect(Collectors.toList());
        expectedStates.forEach(multiSelect::selectByValue);
        List<String> selectedStates = multiSelect.getAllSelectedOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());
        assertTrue(expectedStates.containsAll(selectedStates));
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}