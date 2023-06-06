package org.selenium.framework.steps;

import io.cucumber.java.en.And;
import org.selenium.framework.pages.MainPage;

public class MainSteps {

    final MainPage mainPage = new MainPage();

    @And(value = "^Переходим на страничку 'Командировки'$")
    public void getBusinessTrips() {
        mainPage.goToBusinessTrips();
    }


    @And(value = "^Нажимаем кнопку 'Создать командиовку'$")
    public void createBusinessTrip() {
        mainPage.createBusinessTrip();
    }

}
