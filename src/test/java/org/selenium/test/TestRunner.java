package org.selenium.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestRunner {

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

//        Step 2: Navigate to page "Командировки"
        WebElement costsLists = webDriver.findElement(By.xpath(
                "//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        costsLists.click();
        wait.until(ExpectedConditions.visibilityOf(costsLists.findElement(By.xpath(
                "./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        webDriver.findElement(By.xpath("//span[text()='Командировки']")).click();

        loadingWait();

        Assertions.assertEquals("Все Командировки", webDriver.findElement(By.xpath(
                        "//h1[@class='oro-subtitle']")).getText(),
                "Страница не содержит элемента c названием: Все Командировки");

//        Step 3: Filtration
        webDriver.findElement(By.xpath(
                "//div[@class='filter-item oro-drop']/div[contains(text(), 'Стадия')]")).click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(
                "//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]"))));
        webDriver.findElement(By.xpath(
                "//div[contains(@class, 'ui-multiselect-menu ui-corner-all')]//input[@type='search']"
        )).sendKeys("Согласование с ОСР");
        webDriver.findElement(By.xpath(
                "//label/input[@title='Согласование с ОСР' and @type='checkbox']"
        )).click();
        loadingWait();

        String id = webDriver.findElement(By.xpath(
                "//td[text()='Питер']//parent::tr/td[contains(@class, 'name')]"
        )).getText();
        webDriver.findElement(By.xpath(
                "//div[contains(@class, 'btn filter-criteria-selector') and contains(text(), 'Номер')]"
        )).click();
        webDriver.findElement(By.xpath(
                "//input[@type='text' and @name='value']"
        )).sendKeys(id, Keys.ENTER);
        loadingWait();

//        Step 4: Going on a specified field
        webDriver.findElement(By.xpath(String.format("//td[contains(@class, 'grid-body-cell-name') and text()='%s']", id)
        )).click();
        loadingWait();

        String actualId = webDriver.findElement(By.xpath(
                "//h1[@class='user-name']")).getText();

        Assertions.assertEquals(id, actualId,
                "Страница открытой командировки не содержит: " + id);

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
