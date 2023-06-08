package org.selenium.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class SearchResultPage extends BasePage {

    private String savedSearchResult;

    @FindBy(xpath = "//div[contains(@class, 'ListingRenderer_row')]//h6")
    private List<WebElement> searchResultRow;

    @FindBy(xpath = "//span[contains(@class, 'CardText_skeleton')]")
    private WebElement checkLoadPageElement;

    @FindBy(xpath = "//input[@id='searchInput']")
    private WebElement searchInput;


    public void saveFirstSearchResult() {
        wait.until(invisibilityOf(checkLoadPageElement));
        savedSearchResult = searchResultRow.get(0).getAttribute("title");
    }

    public void enterFilterByName() {
        searchInput.sendKeys(savedSearchResult, Keys.ENTER);
    }

    public void checkSearchResultAfterFilterProductByName() {
        wait.until(invisibilityOf(checkLoadPageElement));
        if (searchResultRow.size() != 1) {
            Assertions.fail("В результирующем поиске вышло: " + searchResultRow.size() + " элементов\n" +
                    "А ожидалось: 1");
        }
    }

    public void checkSearchResultWithSaveValue() {
        if (!savedSearchResult.equals(searchResultRow.get(0).getAttribute("title"))) {
            Assertions.fail("В прошлом  шаге запомнили следующие название товара: " + savedSearchResult + "\n" +
                    "А при поиске нашелся товар с именем: " + searchResultRow.get(0).getAttribute("title"));
        }
    }
}
