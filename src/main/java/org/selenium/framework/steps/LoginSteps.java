package org.selenium.framework.steps;

import org.selenium.framework.pages.LoginPage;

public class LoginSteps {

    public MainSteps auth(String login, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.getAuthorization(login, password);

        return new MainSteps();
    }
}
