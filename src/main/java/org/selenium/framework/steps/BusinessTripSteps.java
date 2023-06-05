package org.selenium.framework.steps;

import org.selenium.framework.pages.BusinessTripPage;

public class BusinessTripSteps {

    final BusinessTripPage tripPage = new BusinessTripPage();


    public BusinessTripSteps getSubDivision() {
        tripPage.chooseSubDivision();
        tripPage.chooseOrganization();

        return new BusinessTripSteps();
    }

    public BusinessTripSteps pushCheckBox() {
        tripPage.ticketsCheckBoxClick();

        return new BusinessTripSteps();
    }

    public BusinessTripSteps getDepartureCity(String country, String city) {
        tripPage.chooseDepartureCity(country, city);

        return new BusinessTripSteps();
    }

    public BusinessTripSteps getArrivalCity(String country, String city) {
        tripPage.chooseArrivalCity(country, city);

        return new BusinessTripSteps();
    }

    public BusinessTripSteps getDepartureDate() {
        tripPage.chooseDepartureDate();

        return new BusinessTripSteps();
    }

    public BusinessTripSteps getReturnDate(int dayshift) {
        tripPage.chooseReturnDate(dayshift);

        return new BusinessTripSteps();
    }

    public BusinessTripSteps saveAndCloseTrip() {
        tripPage.saveTrip();

        return new BusinessTripSteps();
    }
}
