package org.selenium.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//form[@id='login-form']")
    private WebElement loginWindow;

    @FindBy(xpath = "//input[@id='prependedInput' and @name='_username']")
    private WebElement loginRow;

    @FindBy(xpath = "//input[@id='prependedInput2' and @name='_password']")
    private WebElement passwordRow;

    @FindBy(xpath = "//button[@id='_submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//h1[@class='oro-subtitle']")
    private WebElement title;

    public void getAuthorization(String login, String password) {
        wait.until(visibilityOf(loginWindow));
        loginRow.sendKeys(login);
        passwordRow.sendKeys(password);
        loginButton.click();
        wait.until(visibilityOf(title));

    }

}
