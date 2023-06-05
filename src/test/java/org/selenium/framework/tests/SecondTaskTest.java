package org.selenium.framework.tests;

import org.junit.jupiter.api.DisplayName;
import org.selenium.framework.basetestclass.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.selenium.framework.extension.DriverExtension;

@ExtendWith(DriverExtension.class)
public class SecondTaskTest extends BaseTest {
    @Test
    @DisplayName("Тест практического задания №2")
    public void test() {

        manger.getLoginSteps()
                .auth(
                        properties.getProperty("auth.login"),
                        properties.getProperty("auth.password"))
                .getBusinessTrips()
                .createBusinessTrip()
                .getSubDivision()
                .pushCheckBox()
                .getDepartureCity("UK", "London")
                .getArrivalCity("Russia", "Moscow")
                .getDepartureDate()
                .getReturnDate(14)
                .saveAndCloseTrip();


//        Для того чтобы успеть посмотреть как все накликалось
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
