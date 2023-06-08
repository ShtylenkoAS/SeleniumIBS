package org.selenium.framework.steps;

import io.cucumber.java.en.And;
import org.selenium.framework.pages.MainPage;

public class MainSteps {

    final MainPage mainPage = new MainPage();

    @And(value = "^Нажимаем на кнопку 'Каталог'$")
    public void clickCatalogBtn() {
        mainPage.clickCatalogBtn();
    }


    @And(value = "^Выбираем категорию: \"([^\"]*)\"$")
    public void getCategory(String name) {
        mainPage.selectCategoryByText(name);
    }

    @And(value = "^Выбираем подкатегорию: \"([^\"]*)\"$")
    public void getSubCategory(String name){
        mainPage.selectSubCategoryByText(name);
    }

}
