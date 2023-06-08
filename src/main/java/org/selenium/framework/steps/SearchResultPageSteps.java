package org.selenium.framework.steps;

import io.cucumber.java.en.And;
import org.selenium.framework.pages.SearchResultPage;

public class SearchResultPageSteps {

    final SearchResultPage searchResultPage = new SearchResultPage();

    @And(value = "Сохраняем наименование первого товара в списке")
    public void saveParameter() {
        searchResultPage.saveFirstSearchResult();
    }

    @And(value = "^Вводим в поисковую строку наименование первого товара в списке$")
    public void enterFirstSearchResultToMainInputField() {
        searchResultPage.enterFilterByName();
    }

    @And(value = "^Проверяем, что в поисковой выдаче не более 1 товара$")
    public void checkCntElementAfterSearch() {
        searchResultPage.checkSearchResultAfterFilterProductByName();
    }

    @And(value = "^Проверяем, что наименование товара соответствует сохраненному значению$")
    public void checkSaveValueWithFirstRow() {
        searchResultPage.checkSearchResultWithSaveValue();
    }
}
