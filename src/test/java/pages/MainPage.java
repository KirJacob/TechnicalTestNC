package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class MainPage extends BasePage{

    //    -------------- WEB ELEMENTS -------------------
    public SelenideElement searchFld () {
        return $(xpath("//*[contains(@id,'searchbox')]"));
    }

    public SelenideElement confirmProposedCity() {
        return $(xpath("//*[contains(@class,'js-success')]"));
    }

    public SelenideElement searchResultPopup () {
        return $(xpath("//ul[contains(@class,'ui-autocomplete')]"));
    }

    public SelenideElement getExactResultFromSearch(String searchQuery, String exactQuery) {
        ElementsCollection results = $$(xpath("//*[contains(@class,'ui-autocomplete')]//*[contains(text(),'"
                + searchQuery + "')]/../.."));
        SelenideElement result = null;
        for (SelenideElement element : results){
            if (element.getText().contains(exactQuery))
                result = element;
        }
        return result;
    }

    //    -------------- METHODS -------------------
    public void setSearchFld (String searchQuery) {
        setFieldVal(searchFld(), searchQuery);
    }

    public void chooseCityPopup() {
        click(confirmProposedCity());
    }

    public void clickExactSearchResult(String searchQuery, String exactQuery) {
        click(getExactResultFromSearch(searchQuery, exactQuery));
    }
}
