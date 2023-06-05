package org.selenium.framework.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropManager {
    private final Properties properties = new Properties();

    private static TestPropManager INSTANCE = null;

    public static TestPropManager getInstance() {
        if (INSTANCE == null) {
            return new TestPropManager();
        }
        return INSTANCE;
    }

    private TestPropManager() {
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
