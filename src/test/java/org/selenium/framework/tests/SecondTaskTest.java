package org.selenium.framework.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.selenium.framework.basetestclass.BaseTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.selenium.framework.extension.AllureExtension;
import org.selenium.framework.extension.DriverExtension;

@DisplayName(value = "Сценарий прогона второго теста")
@ExtendWith({DriverExtension.class, AllureExtension.class})
public class SecondTaskTest extends BaseTest {
    @Test
    @Severity(value = SeverityLevel.MINOR)
    @Description(value = "Тест создания командировки")
    @DisplayName("Тест создания командировки через Page-Object & Page-Factory")
    @Link(value = "https://test.link.for.jira.test=not_working")
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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
