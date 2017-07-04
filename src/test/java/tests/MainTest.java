package tests;

import helpers.CommonWD;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProductPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;

public class MainTest extends BaseTest {

    private MainPage mainPage;
    private ProductPage productPage;
    
    @BeforeClass
    public void setUp() {
        open(mainURL);
    }

    @Test(priority = 1)
    public void mainTest(){
        String searchKeyWord = "iPhone";
        String productName = "iPhone 7 32GB";

        mainPage = open(mainURL, MainPage.class);
        waitForElement(mainPage.searchFld());
        mainPage.chooseCityPopup();
        mainPage.setSearchFld(searchKeyWord);
        waitForElement(mainPage.searchResultPopup());
        mainPage.clickExactSearchResult(searchKeyWord, productName);

        productPage = open(url(), ProductPage.class);
        productPage.clickAllOffersLnk();
        waitForElement(productPage.offersTable());
        productPage.clickChosenLink();

        CommonWD.waitForLoad(getWebDriver());
        System.out.println("Chosen Product Page Title=" + getWebDriver().getTitle());
        assertTrue(getWebDriver().getTitle().contains(productName));
    }
}
