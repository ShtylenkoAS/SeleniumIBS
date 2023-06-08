package org.selenium.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.framework.managers.DriverManager;

import java.time.Duration;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class BasePage {

    protected static WebDriver webDriver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));


    public BasePage() {
        initElements(webDriver, this);
    }

    public void waitLoadingElements(WebElement webElement) {
        wait.until(visibilityOf(webElement));
    }
}
