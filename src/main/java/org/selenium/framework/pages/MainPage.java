package org.selenium.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class MainPage extends BasePage {

    @FindBy(xpath = "//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']")
    private WebElement costs;

    @FindBy(xpath = "//span[@class='title' and contains(text(), 'Расходы')]/ancestor::li/ul[@class='dropdown-menu menu_level_1']")
    private WebElement costMenu;

    @FindBy(xpath = "//span[text()='Командировки']")
    private WebElement businessTrips;

    @FindBy(xpath = "//a[contains(@class, 'btn back icons') and text()='Создать командировку']")
    private WebElement createBusinessTripBtn;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement titleText;

    public void goToBusinessTrips() {
        checkStartTitleText();
        costs.click();
        wait.until(visibilityOf(costMenu));
        businessTrips.click();
//        loadingWait();        Если его включить, то будет думать на стадии нажатия кнопки "Создать командировку"
    }

    public void createBusinessTrip() {
        createBusinessTripBtn.click();
//        loadingWait();        Если включить его, то будет думать на стадии заполнения полей в разделе "Создать командировку"
    }

    public void checkStartTitleText() {
        Assertions.assertEquals("Панель быстрого запуска", titleText.getText(),
                "Страница не содержит элемента c названием: Панель быстрого запуска");
    }

}
