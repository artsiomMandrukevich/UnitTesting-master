package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {

    private final WebDriver driver;
    private final static String PATH = "src/test/screenshots/";

    public Screenshot(WebDriver driver) {
        this.driver = driver;
    }

    public void captureScreenshot(String screenshotName) throws IOException {
        File imageFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotFileName = PATH + getTimePrefix() + "_" + screenshotName + ".png";
        File fileName = new File(screenshotFileName);
        FileUtils.moveFile(imageFile, fileName);
    }

    public String getTimePrefix() {
        Date date = new Date();
        SimpleDateFormat timePrefix = new SimpleDateFormat("ddMMyy_HHmmss");
        return timePrefix.format(date);
    }
}
