package org.selenium.framework.steps;

import io.qameta.allure.Step;
import org.selenium.framework.pages.LoginPage;

public class LoginSteps {

    @Step(value = "Проходим авторизацию")
    public MainSteps auth(String login, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.getAuthorization(login, password);

        return new MainSteps();
    }
}
