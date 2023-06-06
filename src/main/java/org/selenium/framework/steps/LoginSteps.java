package org.selenium.framework.steps;

import io.cucumber.java.en.And;
import org.selenium.framework.pages.LoginPage;

public class LoginSteps {

    @And(value = "^Проходим авторизацию используя логин: \"([^\"]*)\" и пароль: \"([^\"]*)\"$")
    public void auth(String login, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.getAuthorization(login, password);

    }
}
