package org.selenium.framework.steps;

import io.qameta.allure.Step;
import org.selenium.framework.pages.BusinessTripPage;

public class BusinessTripSteps {

    final BusinessTripPage tripPage = new BusinessTripPage();


    @Step(value = "Выбираем подразделения")
    public BusinessTripSteps getSubDivision() {
        tripPage.chooseSubDivision();
        tripPage.chooseOrganization();

        return new BusinessTripSteps();
    }

    @Step(value = "Прожимаем чекбокс 'Заказ билетов'")
    public BusinessTripSteps pushCheckBox() {
        tripPage.ticketsCheckBoxClick();

        return new BusinessTripSteps();
    }

    @Step(value = "Прописываем город выбытия - Страна: {0}, Город: {1}")
    public BusinessTripSteps getDepartureCity(String country, String city) {
        tripPage.chooseDepartureCity(country, city);

        return new BusinessTripSteps();
    }

    @Step(value = "Прописываем город прибытия - Страна: {0}, Город: {1}")
    public BusinessTripSteps getArrivalCity(String country, String city) {
        tripPage.chooseArrivalCity(country, city);

        return new BusinessTripSteps();
    }

    @Step(value = "Прописываем дату отправления")
    public BusinessTripSteps getDepartureDate() {
        tripPage.chooseDepartureDate();

        return new BusinessTripSteps();
    }

    @Step(value = "Прописываем дату возвращения")
    public BusinessTripSteps getReturnDate(int dayshift) {
        tripPage.chooseReturnDate(dayshift);

        return new BusinessTripSteps();
    }

    @Step(value = "Нажимаем кнопку 'Сохранить и закрыть'")
    public BusinessTripSteps saveAndCloseTrip() {
        tripPage.saveTrip();

        return new BusinessTripSteps();
    }
}
