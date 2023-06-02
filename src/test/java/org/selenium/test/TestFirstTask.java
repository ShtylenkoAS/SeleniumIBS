package org.selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestFirstTask {
    private final String URL = "http://training.appline.ru/user/login";
    private final String login = "Taraskina Valeriya";
    private final String password = "testing";

    WebDriver webDriver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));


    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/webdriver/chromedriver.exe");
        webDriver.manage().window().maximize();
        webDriver.get(URL);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    public void test() {
        //        Step 1: Authorization
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//form[@id='login-form']"))));
        webDriver.findElement(By.xpath("//input[@id='prependedInput' and @name='_username']"))
                .sendKeys(login);

        webDriver.findElement(By.xpath("//input[@id='prependedInput2' and @name='_password']"))
                .sendKeys(password);

        webDriver.findElement(By.xpath("//button[@id='_submit']"))
                .click();

        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//h1[@class='oro-subtitle']"))));


        WebElement checkTitle = webDriver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        Assertions.assertEquals("Панель быстрого запуска", checkTitle.getText(),
                "Страница не содержит элемента c названием: Панель быстрого запуска");

    }

    @AfterEach
    public void after() {
        webDriver.quit();
    }

    private void loadingWait() {
        wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath(
                "//div[@class='loader-mask shown']"))));
    }

}
