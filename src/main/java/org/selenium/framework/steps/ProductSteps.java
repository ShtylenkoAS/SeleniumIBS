package org.selenium.framework.steps;

import io.cucumber.java.en.And;
import org.selenium.framework.pages.ProductPage;

public class ProductSteps {

    final ProductPage productPage = new ProductPage();

    @And(value = "^Выбираем минимальную цену: \"([^\"]*)\"$")
    public void getMinPrice(String price) {
        productPage.chooseMinPrice(price);
    }

    @And(value = "^Прожимаем чекбокс: \"([^\"]*)\"$")
    public void clickCheckBox(String checkBoxName) {
        productPage.clickCheckBox(checkBoxName);
    }

    @And(value = "^Проверяем, что паджинация страницы = 24$")
    public void checkPagination() {
        productPage.checkPagination();
    }

}
