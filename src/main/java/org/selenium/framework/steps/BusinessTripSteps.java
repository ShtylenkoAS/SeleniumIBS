package org.selenium.framework.steps;

import io.cucumber.java.en.And;
import org.selenium.framework.pages.BusinessTripPage;

public class BusinessTripSteps {

    final BusinessTripPage tripPage = new BusinessTripPage();


    @And(value = "^Выбираем подразделения$")
    public void getSubDivision() {
        tripPage.chooseSubDivision();
        tripPage.chooseOrganization();
    }


    @And(value = "^Прожимаем чекбокс 'Заказ билетов'$")
    public void pushCheckBox() {
        tripPage.ticketsCheckBoxClick();
    }


    @And(value = "^Прописываем город выбытия - Страна: \"([^\"]*)\", Город: \"([^\"]*)\"$")
    public void getDepartureCity(String country, String city) {
        tripPage.chooseDepartureCity(country, city);
    }

    @And(value = "^Прописываем город прибытия - Страна: \"([^\"]*)\", Город: \"([^\"]*)\"$")
    public void getArrivalCity(String country, String city) {
        tripPage.chooseArrivalCity(country, city);
    }


    @And(value = "^Прописываем дату отправления$")
    public void getDepartureDate() {
        tripPage.chooseDepartureDate();
    }


    @And(value = "^Прописываем дату возвращения через \"([^\"]*)\" дней$")
    public void getReturnDate(int dayshift) {
        tripPage.chooseReturnDate(dayshift);
    }


    @And(value = "^Нажимаем кнопку 'Сохранить и закрыть'$")
    public void saveAndCloseTrip() {
        tripPage.saveTrip();
    }
}
