package org.selenium.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//li[@itemprop='itemListElement']/a/meta[@content='3']/ancestor::a")
    private WebElement checkLoad;

    @FindBy(xpath = "//div[contains(@class, 'RangeSelector_inputs')]/input[contains(@class, 'RangeSelector_input') and @name='min']")
    private WebElement minPriceRow;

    @FindBy(xpath = "//span[contains(@class, 'Checkbox_label')]/label[contains(@class, 'small')]/following::span[1]/label")
    private List<WebElement> allLeftCheckBoxList;

    @FindBy(xpath = "//span[contains(@class, 'countSetter')]")
    private WebElement paginationCnt;

    @FindBy(xpath = "//div[contains(@class, 'ListingRenderer_row')]//h6")
    private List<WebElement> searchResultRow;

    @FindBy(xpath = "//button[contains(@class, 'buyButton')]")
    private WebElement findBtn;

    public void chooseMinPrice(String price) {
        waitLoadingElements(checkLoad);
        minPriceRow.sendKeys(price);
    }

    public void clickCheckBox(String name) {
        for (WebElement itemMenu : allLeftCheckBoxList) {
            if (itemMenu.getText().contains(name)) {
                itemMenu.click();
                return;
            }
        }
        Assertions.fail("Чекбокс с именем: " + name + " не найден на странице");
    }

    public void checkPagination() {
        waitLoadingElements(findBtn);
        if (!paginationCnt.getText().contains("по 24")) {
            Assertions.fail("При поиске товаров их количество их количество на странице ожидалось ожидалось: 'по 24', а пришло: "
                    + "'" + paginationCnt.getText() + "'");
        }
    }

}
