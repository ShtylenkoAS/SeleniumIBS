package org.selenium.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class, 'NavigationBar_burgerButton__PDGsX')]")
    private WebElement catalogBtn;

    @FindBy(xpath = "//a[contains(@class, 'Catalog_mainCategory')]/div")
    private List<WebElement> catalogMainCategoryList;

//    Элемент по которому сверяю что страница загружена
    @FindBy(xpath = "//a[contains(@class, 'BreadCrumbs')]/span[@itemprop='name']")
    private WebElement checkLoad;

    @FindBy(xpath = "//div[contains(@class, 'CardCategory_text')]/p[contains(@class, 'CardCategory_title')]")
    private List<WebElement> catalogSubCategoryList;

    public void clickCatalogBtn() {
        catalogBtn.click();
    }

    public void selectCategoryByText(String nameCategory) {
        for (WebElement itemMenu : catalogMainCategoryList) {
            if (itemMenu.getText().contains(nameCategory)) {
                itemMenu.click();
                return;
            }
        }
        Assertions.fail("Секция в каталоге с именем: " + nameCategory + " не найдена на странице");
    }

    public void selectSubCategoryByText(String nameCategory) {
        waitLoadingElements(checkLoad);
        for (WebElement itemMenu : catalogSubCategoryList) {
            if (itemMenu.getText().contains(nameCategory)) {
                itemMenu.click();
                return;
            }
        }
        Assertions.fail("Категория с именем: " + nameCategory + " не найдена на странице");
    }

}
