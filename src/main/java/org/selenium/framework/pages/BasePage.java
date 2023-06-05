package org.selenium.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.selenium.framework.managers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class BasePage {

    @FindBy(xpath = "//div[@class='loader-mask shown']")
    private WebElement loadingWindow;

    protected static WebDriver webDriver = DriverManager.getDriver();
    protected WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));

    protected Actions actions = new Actions(webDriver);

    public BasePage() {
        initElements(webDriver, this);
    }

    public void loadingWait() {
        wait.until(invisibilityOf(loadingWindow));
    }


    /**
     * @return current system date in format dd.MM.yyyy
     */
    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.format(date);

        return dateFormat.format(date);
    }

    /**
     * @param dayShift - amount of days
     * @return sysdate + dayShift date in format dd.MM.yyyy
     */
    public String getCustomDate(Integer dayShift) {
        Date sysdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sysdate);
        calendar.add(Calendar.DATE, dayShift);

        return dateFormat.format(calendar.getTime());
    }

    /**
     * @param webElement to be clicked twice
     */
    public void doubleClick(WebElement webElement) {
        actions.doubleClick(webElement).perform();
    }

    public void checkTextElement(String expected, WebElement webElement) {
        String webElementText = webElement.getText();
        Assertions.assertEquals(expected, webElementText,
                "Значение текста в проверяемом поле не соответсвует ожидаемому");
    }

}
