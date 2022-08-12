import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EmployeesFilter {
    private static WebDriver driver;

    private static final By SHOW_ENTRIES_SELECTOR = By.cssSelector("select[name='example_length']");
    private static final By SHOWING_ITEMS = By.cssSelector("div[role='status']");
    private static final By TABLE_ROWS = By.xpath("//table/tbody//tr[@role='row']");
    private static final By TABLE_COLUMNS = By.xpath("//table/thead//th");

    private List<Employee> filterEmployeesByAgeAndSallary(List<Employee> employeeList, int ageValue, int salaryValue) {
        List<Employee> filteredList = new ArrayList<>();

        for (Employee employee : employeeList) {
            if (employee.getAge() > ageValue && employee.getSalary() <= salaryValue)
                filteredList.add(employee);
        }

        return filteredList;
    }

    private List<Employee> readDataFromWebTable(WebDriver driver) {
        int rowsSize = driver.findElements(TABLE_ROWS).size();
        int columnsSize = driver.findElements(TABLE_COLUMNS).size();
        List<Employee> listOfEmployees = new ArrayList<>();

        for (int i = 1; i <= rowsSize; i++) {
            Employee rowSal = new Employee();
            String[] row = new String[columnsSize];
            for (int j = 1; j <= columnsSize; j++) {
                row[j - 1] = driver.findElement(By.xpath("//tr[" + i + "]/td[" + j + "]")).getText();
            }
            rowSal.setName(row[0]);
            rowSal.setPosition(row[1]);
            rowSal.setOffice(row[2]);
            rowSal.setAge(row[3]);
            rowSal.setStartDate(row[4]);
            rowSal.setSalary(row[5]);
            listOfEmployees.add(rowSal);
        }

        return listOfEmployees;
    }

    @Before
    public void startDriver() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void waitForUser() {
        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");

        Select select = new Select(driver.findElement(SHOW_ENTRIES_SELECTOR));
        select.selectByValue("10");

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until((ExpectedCondition<Boolean>) d -> d != null &&
                        d.findElement(SHOWING_ITEMS).getText().contains("Showing 1 to 10"));

        List<Employee> listFromWeb = readDataFromWebTable(driver);

        for (Employee finalList : filterEmployeesByAgeAndSallary(listFromWeb, 30, 300000)) {
            System.out.println(finalList.getName() + " " + finalList.getPosition() + " " + finalList.getOffice());
        }
    }

    @After
    public void stopDriver() {
        driver.quit();
    }
}
