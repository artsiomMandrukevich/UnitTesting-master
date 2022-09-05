package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static final Properties prop = new Properties();
    private static final String filePath = "src/main/resources/";
    private static final String fileName = "config.properties";

    public String getPropertyByName(String propertiesName) {
        String propertyValue;
        InputStream inputPropertiesFile;
        try {
            inputPropertiesFile = new FileInputStream(filePath + fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            prop.load(inputPropertiesFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        propertyValue = prop.getProperty(propertiesName);
        return propertyValue;
    }
}
