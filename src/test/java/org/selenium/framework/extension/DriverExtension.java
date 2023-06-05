package org.selenium.framework.extension;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.selenium.framework.managers.DriverManager;

public class DriverExtension implements BeforeAllCallback, AfterAllCallback {
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        DriverManager.quitDriver();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        DriverManager.getDriver();
    }
}
