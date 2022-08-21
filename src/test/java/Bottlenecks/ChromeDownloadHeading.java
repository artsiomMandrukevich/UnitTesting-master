package Bottlenecks;

import driver.Driver;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpHead;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.HttpResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromeDownloadHeading {

    public WebDriver driver;

    @Test
    public void downloadFileRevisitedTest() throws Exception {
        driver = Driver.getDriver();
        driver.get("http://the-internet.herokuapp.com/download");
        String link = driver.findElement(By.cssSelector(".example a:nth-of-type(1)")).getAttribute("href");

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpHead request = new HttpHead(link);
        HttpResponse response = httpClient.execute(request);

        String contentType = response.getFirstHeader("Content-Type").getValue();
        int contentLength = Integer.parseInt(response.getFirstHeader("Content-Length").getValue());
        System.out.println("contentType " + contentType);
        System.out.println("contentLength " + contentLength);
        assertEquals(contentType, "application/octet-stream");
        assertEquals(contentLength, 301966);

    }


    @AfterEach
    public void quitDriver() {
        Driver.tearDown();
    }

}