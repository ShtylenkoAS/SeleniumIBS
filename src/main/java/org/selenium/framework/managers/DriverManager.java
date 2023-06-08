package org.selenium.framework.managers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Properties;

public class DriverManager {

    private static WebDriver webDriver;

    private static Properties properties = TestPropManager.getInstance().getProperties();


    public static void initDriver() {
        if (properties.getProperty("type.browser").equals("chrome")) {
            System.setProperty(properties.getProperty("chrome.driver.sys.property"), properties.getProperty("chrome.driver.path"));
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.get(properties.getProperty("host.url"));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        } else {
            Assertions.fail(("Тип браузера '" + properties.getProperty("type.browser") + "' не поддерживается для текущего теста"));
        }

    }

    public static WebDriver getDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }

    public static void closeDriver() {
        if (webDriver != null) {
            webDriver.close();
        }
        webDriver = null;
    }

}
