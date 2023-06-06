package org.selenium.framework.steps;

import io.qameta.allure.Step;
import org.selenium.framework.pages.MainPage;

public class MainSteps {

    final MainPage mainPage = new MainPage();

    @Step(value = "Переходим на страничку 'Командировки'")
    public MainSteps getBusinessTrips() {
        mainPage.goToBusinessTrips();

        return new MainSteps();
    }

    @Step(value = "Нажимаем кнопку 'Создать командиовку'")
    public BusinessTripSteps createBusinessTrip() {
        mainPage.createBusinessTrip();

        return new BusinessTripSteps();
    }

}
