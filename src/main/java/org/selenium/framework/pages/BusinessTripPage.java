package org.selenium.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class BusinessTripPage extends BasePage {
    @FindBy(xpath = "//h1[@class='user-name']")
    private WebElement titleName;

    @FindBy(xpath = "//select[@data-name='field__business-unit']")
    private WebElement subdivisionMenu;

    @FindBy(xpath = "//select[@data-name='field__business-unit']/option[@value='7']")
    private WebElement subdivisionSelector;

    @FindBy(xpath = "//a[@id='company-selector-show']")
    private WebElement openListBtn;

    @FindBy(xpath = "//span[@class='select2-chosen']")
    private WebElement openListSelector;

    @FindBy(xpath = "//input[contains(@id, 'crm_business_trip_company') and @data-ftid='crm_business_trip_company']")
    private WebElement hostOrganization;

    @FindBy(xpath = "//ul[@class='select2-results']/li//div[@class='select2-result-label' and contains(text(), 'Хром')]")
    private WebElement chooseSpecifiedField;

    @FindBy(xpath = "//input[@data-ftid='crm_business_trip_tasks_1']")
    private WebElement orderTicketsCheckBox;

    @FindBy(xpath = "//input[@name='crm_business_trip[departureCity]']")
    private WebElement departureCityRow;

    @FindBy(xpath = "//input[@name='crm_business_trip[arrivalCity]']")
    private WebElement arrivalCityRow;

    @FindBy(xpath = "//input[@placeholder='Укажите дату' and contains(@name, 'departure')]")
    private WebElement departureDateField;

    @FindBy(xpath = "//input[@placeholder='Укажите дату' and contains(@name, 'returnDatePlan')]")
    private WebElement returnDateField;

    @FindBy(xpath = "//div[@class='btn-group']/button[@class='btn btn-success action-button' and contains(text(), 'и закрыть')]")
    private WebElement saveAndCloseBtn;

    @FindBy(xpath = "//span[@class='validation-failed' and text()='Список командируемых сотрудников не может быть пустым']")
    private WebElement warningMsg;

    public void checkTitleText() {
        Assertions.assertEquals("Создать командировку", titleName.getText(),
                "Страница не содержит заголовка c названием: Создать командировку");
    }

    public void chooseSubDivision() {
        checkTitleText();
        subdivisionMenu.click();
        subdivisionSelector.click();
    }

    public void chooseOrganization() {
        openListBtn.click();
        openListSelector.click();
        wait.until(visibilityOf(chooseSpecifiedField));
        chooseSpecifiedField.click();
    }

    public void ticketsCheckBoxClick() {
        orderTicketsCheckBox.click();
    }

    public void chooseDepartureCity(String country, String city) {
        String row = departureCityRow.getAttribute("value");
        for (int i = 0; i < row.length(); i++) {
            departureCityRow.sendKeys(Keys.BACK_SPACE);
        }
        departureCityRow.sendKeys(country + ", " + city);
    }

    public void chooseArrivalCity(String country, String city) {
        arrivalCityRow.sendKeys(country + ", " + city);
    }

    public void chooseDepartureDate() {
        departureDateField.sendKeys(getCurrentDate());
    }

    public void chooseReturnDate(int dayshift) {
        returnDateField.sendKeys(getCustomDate(dayshift));
    }

    public void saveTrip() {
        doubleClick(saveAndCloseBtn);
        checkTextField();
    }

    public void checkTextField() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Отдел внутренней разработки", subdivisionSelector.getText(),
                        "В поле 'Подразделение' не выбран: Отдел внутренней разработки"),
                () -> Assertions.assertEquals("(Хром) Призрачная Организация Охотников", hostOrganization.getAttribute("value"),
                        "В поле 'Укажите организацию' не выбрана организация: (Хром) Призрачная Организация Охотников"),
                () -> Assertions.assertTrue(orderTicketsCheckBox.getAttribute("value").equals("1"),
                        "Чекбокс 'Заказ билетов' не нажат"),
                () -> Assertions.assertNotNull(departureCityRow.getAttribute("value"),
                        "Поле 'Город выбытия' не заполнено"),
                () -> Assertions.assertNotNull(arrivalCityRow.getAttribute("value"),
                        "Поле 'Город прибытия' не заполнено"),
                () -> Assertions.assertNotNull(departureDateField.getAttribute("value"),
                        "Поле 'Планируемая дата выезда' не заполнено"),
                () -> Assertions.assertNotNull(returnDateField.getAttribute("value"),
                        "Поле 'Планируемая дата возвращения' не заполнено"),
                () -> Assertions.assertTrue(warningMsg.isDisplayed(),
                        "сообщение: \"Список командируемых сотрудников не может быть пустым\" не появилось")
//                ,() -> Assertions.fail("Тестовый фэйл - проверка того что делается скрин")
        );
    }
}
