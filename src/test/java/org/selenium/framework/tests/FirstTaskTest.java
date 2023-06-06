package org.selenium.framework.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@DisplayName(value = "Сценарий прогона первого теста")
public class FirstTaskTest {

    private final String URL = "http://training.appline.ru/user/login";
    private String login;
    private String password;

    Properties properties = new Properties();
    FileInputStream fileInputStream;

    WebDriver webDriver = new ChromeDriver();
    Actions actions = new Actions(webDriver);
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));


    @BeforeEach
    public void before() {
        try {
            fileInputStream = new FileInputStream("src/main/resources/application.properties");
            properties.load(fileInputStream);

            login = properties.getProperty("auth.login");
            password = properties.getProperty("auth.password");

        } catch (IOException e) {
            System.err.println("ERROR: property file does not exists");
        }

        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver.path"));
        webDriver.manage().window().maximize();
        webDriver.get(URL);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @Test
    @DisplayName("Тест создания командировки")
    public void test() {
//        Step 1: Перейти на страницу http://training.appline.ru/user/login Пройти авторизацию
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//form[@id='login-form']"))));
        webDriver.findElement(By.xpath("//input[@id='prependedInput' and @name='_username']"))
                .sendKeys(login);
        webDriver.findElement(By.xpath("//input[@id='prependedInput2' and @name='_password']"))
                .sendKeys(password);

        webDriver.findElement(By.xpath("//button[@id='_submit']"))
                .click();
        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//h1[@class='oro-subtitle']"))));


//        Step 2: Проверить наличие на странице заголовка Панель быстрого запуска
        WebElement checkTitle = webDriver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        Assertions.assertEquals("Панель быстрого запуска", checkTitle.getText(),
                "Страница не содержит элемента c названием: Панель быстрого запуска");


//        Step3: В выплывающем окне раздела Расходы нажать на Командировки
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


//        Step 4: Нажать на Создать командировку
        webDriver.findElement(By.xpath(
                "//a[contains(@class, 'btn back icons') and text()='Создать командировку']"
        )).click();
        loadingWait();


//        Step 5: Проверить наличие на странице заголовка "Создать командировку"
        String checkPageTitle = webDriver.findElement(By.xpath(
                "//h1[@class='user-name']"
        )).getText();

        Assertions.assertEquals("Создать командировку", checkPageTitle,
                "Страница не содержит заголовка c названием: Создать командировку");


//        Step 6: На странице создания командировки заполнить или выбрать поля
//        выбрать Отдел внутренней разработки
        webDriver.findElement(By.xpath(
                "//select[@data-name='field__business-unit']"
        )).click();
        webDriver.findElement(By.xpath(
                "//select[@data-name='field__business-unit']/option[@value='7']"
        )).click();

//        нажать "Открыть список" и в поле "Укажите организацию" выбрать любое значение
        webDriver.findElement(By.xpath(
                "//a[@id='company-selector-show']"
        )).click();

        webDriver.findElement(By.xpath(
                "//span[@class='select2-chosen']"
        )).click();

        wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath(
                "//ul[@class='select2-results']/li//div[@class='select2-result-label' and contains(text(), 'Хром')]"
        ))));

        webDriver.findElement(By.xpath(
                        "//ul[@class='select2-results']/li//div[@class='select2-result-label' and contains(text(), 'Хром')]"))
                .click();

//        поставить чекбокс на "Заказ билетов"
        webDriver.findElement(By.xpath("//input[@data-ftid='crm_business_trip_tasks_1']")).click();

//         Указать города выбытия и прибытия
        String departureCity = webDriver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']"))
                .getAttribute("value");

        webDriver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']")).click();

//        Оичстка поля "Город выбытия"
        for (int i = 0; i < departureCity.length(); i++) {
            webDriver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']"))
                    .sendKeys(Keys.BACK_SPACE);
        }
        webDriver.findElement(By.xpath("//input[@name='crm_business_trip[departureCity]']"))
                .sendKeys("Россия, Тамбов");

        webDriver.findElement(By.xpath("//input[@name='crm_business_trip[arrivalCity]']"))
                .sendKeys("Россия, Смоленск");


//        Указать даты выезда и возвращения
        webDriver.findElement(By.xpath("//input[@placeholder='Укажите дату' and contains(@name, 'departure')]"))
                .sendKeys(getCurrentDate());

        webDriver.findElement(By.xpath("//input[@placeholder='Укажите дату' and contains(@name, 'returnDatePlan')]"))
                .sendKeys(getCustomDate(14));


//        Step 7: Проверить, что все поля заполнены правильно
        Assertions.assertEquals("Отдел внутренней разработки", webDriver.findElement(By.xpath(
                "//select[@data-name='field__business-unit']/option[@value='7']")).getText(),
                "В поле 'Подразделение' не выбран: Отдел внутренней разработки");


        Assertions.assertEquals("Открыть \"(Хром) Призрачная Организация Охотников\"", webDriver.findElement(By.xpath(
                "//div/a[contains(text(), 'Хром')]")).getText(),
                "В поле 'Укажите организацию' не выбрана организация: (Хром) Призрачная Организация Охотников");


        Assertions.assertTrue(webDriver.findElement(By.xpath(
                "//input[@data-ftid='crm_business_trip_tasks_1']"
        )).getAttribute("value").equals("1"),
                "Чекбокс 'Заказ билетов' не нажат");


        Assertions.assertEquals("Россия, Тамбов", webDriver.findElement(By.xpath(
                "//input[@name='crm_business_trip[departureCity]']")).getAttribute("value"),
                "Поле 'Город выбытия' не содержит: 'Россия, Тамбов'");

        Assertions.assertEquals("Россия, Смоленск", webDriver.findElement(By.xpath(
                "//input[@name='crm_business_trip[arrivalCity]']")).getAttribute("value"),
                "Поле 'Город прибытия' не содержит: 'Россия, Смоленск'");


        Assertions.assertEquals(getCurrentDate(), webDriver.findElement(By.xpath(
                "//input[@placeholder='Укажите дату' and contains(@name, 'departure')]")).getAttribute("value"),
                "Поле 'Планируемая дата выезда' заполнено некорректно");

        Assertions.assertEquals(getCustomDate(14), webDriver.findElement(By.xpath(
                        "//input[@placeholder='Укажите дату' and contains(@name, 'returnDatePlan')]")).getAttribute("value"),
                "Поле 'Планируемая дата возвращения' заполнено некорректно");


//        Step 8: Нажать "Сохранить и закрыть"
        WebElement saveAndCloseButton = webDriver.findElement(By.xpath(
                "//div[@class='btn-group']/button[@class='btn btn-success action-button' and contains(text(), 'и закрыть')]"));
        actions.doubleClick(saveAndCloseButton).perform();


//        Step9: Проверить, что на странице появилось сообщение: "Список командируемых сотрудников не может быть пустым"
        Assertions.assertTrue(webDriver.findElement(By.xpath(
                "//span[@class='validation-failed' and text()='Список командируемых сотрудников не может быть пустым']"
        )).isDisplayed(),
                "сообщение: \"Список командируемых сотрудников не может быть пустым\" не появилось");

//        Для того чтобы успеть посмотреть как все накликалось
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    public void after() {
        webDriver.quit();
    }

    private void loadingWait() {
        wait.until(ExpectedConditions.invisibilityOf(webDriver.findElement(By.xpath(
                "//div[@class='loader-mask shown']"))));
    }

    /**
     *
     * @return current system date in format dd.MM.yyyy
     */
    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.format(date);

        return dateFormat.format(date);
    }

    /**
     *
     * @param dayShift - amount of days
     * @return sysdate + dayShift date in format dd.MM.yyyy
     */
    private String getCustomDate(Integer dayShift) {
        Date sysdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysdate);
        calendar.add(Calendar.DATE, dayShift);

        return dateFormat.format(calendar.getTime());
    }

}
