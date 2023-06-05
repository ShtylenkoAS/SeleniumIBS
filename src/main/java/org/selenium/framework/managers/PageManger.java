package org.selenium.framework.managers;

import org.selenium.framework.steps.BusinessTripSteps;
import org.selenium.framework.steps.LoginSteps;
import org.selenium.framework.steps.MainSteps;

public class PageManger {

    private static PageManger pageManger;

    private LoginSteps loginSteps;

    private MainSteps mainSteps;

    private BusinessTripSteps tripSteps;

    public static PageManger getPageManger() {
        if (pageManger == null) {
            pageManger = new PageManger();
        }
        return pageManger;
    }

    public LoginSteps getLoginSteps() {
        if (loginSteps == null) {
            loginSteps = new LoginSteps();
        }
        return loginSteps;
    }

    public MainSteps getMainSteps() {
        if (mainSteps == null) {
            mainSteps = new MainSteps();
        }
        return mainSteps;
    }

    public BusinessTripSteps getTripSteps() {
        if (tripSteps == null) {
            tripSteps = new BusinessTripSteps();
        }
        return tripSteps;
    }
}
