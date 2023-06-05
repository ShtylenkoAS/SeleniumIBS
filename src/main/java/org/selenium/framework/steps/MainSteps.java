package org.selenium.framework.steps;

import org.selenium.framework.pages.MainPage;

public class MainSteps {

    final MainPage mainPage = new MainPage();

    public MainSteps getBusinessTrips() {
        mainPage.goToBusinessTrips();

        return new MainSteps();
    }

    public BusinessTripSteps createBusinessTrip() {
        mainPage.createBusinessTrip();

        return new BusinessTripSteps();
    }

}
