package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Offer;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.xpath;

public class ProductPage extends BasePage{

    public SelenideElement allOffersLnk(){
        return $(xpath("(//*[contains(@class,'tabs-link')])[2]"));
    }

    public SelenideElement offersTable(){
        return $(xpath("//*[contains(@class,'new-price-line')]"));
    }

    public void clickAllOffersLnk(){
        click(allOffersLnk());
    }

    public void clickChosenLink(){
        click(getChosenOffersLnk());
    }

    //------------ DATA ANALYZE PART ----------------------
    private int getPriceInt(String priceLine) {
        int result;
        try{
            result = Integer.parseInt(priceLine.replace(",00","").replaceAll(" ",""));
        }catch (Exception e){
            result = Integer.parseInt(priceLine.substring(0, priceLine.indexOf(",")).replaceAll(" ",""));
        }
        return result;
    }
    private int getWarrantyInt(String warrantLine) {
        int result;
        try{
            result = Integer.parseInt(warrantLine.substring(0, warrantLine.indexOf(" мес")));
        }catch (Exception e){
            result = 0;
        }
        return result;
    }
    private int getReviewsInt(String reviewsLine) {
        int result;
        try{
            result = Integer.parseInt(reviewsLine.replace("Отзывов: ", ""));
        }catch (Exception e){
            result = 0;
        }
        return result;
    }

    public SelenideElement getChosenOffersLnk(){
        ElementsCollection collection = $$(xpath("//*[contains(@class,'price-line-shop')]"));
        int colSize = collection.size();
        ArrayList arrayList = new ArrayList();
        String review, warranty, price, name;

        for (int i = 1; i <= colSize; i++){
            ArrayList arrList = new ArrayList();
            review = $(xpath("(//*[contains(@class,'price-line-shop')]//*[contains(@class,'sales-link') or " +
                    "contains(@class,'no-reviews')])[" + i + "]")).getText();
            warranty = $(xpath("(//*[contains(@class,'price-line-shop')]//*[contains(@class,'delivery-th cell4')])["
                    + i + "]")).getText();
            price = $(xpath("(//*[contains(@class,'price-line-shop')]//*[contains(@class,'range-price')])["
                    + i + "]")).getText();
            name = $(xpath("(//*[contains(@class,'price-line-shop')]//*[contains(@class,'shop-title')])["
                    + i + "]")).getText();

            if (name.length()!=0) {
                if((getReviewsInt(review) >= 10) && (getWarrantyInt(warranty) >= 6)) {
                    Offer offer = new Offer(i, name, getReviewsInt(review), getWarrantyInt(warranty), getPriceInt(price));
                    arrayList.add(offer);
                }
            }
        }
        Offer minPriceOffer = null;
        Offer tempOffer;
        ArrayList resultList = new ArrayList();
        System.out.println("----------Print Selected Offers---------------------------");
        for (int i = 0; i < arrayList.size(); i++){
            tempOffer = (Offer)(arrayList.get(i));
            System.out.println(tempOffer);
            if (i == 0)
                minPriceOffer = (Offer)(arrayList.get(i));
            else if (tempOffer.getPrice() < minPriceOffer.getPrice())
                minPriceOffer = tempOffer;
        }
        System.out.println("------------Print Chosen Offer-------------------------");
        System.out.println("minPriceOffer=" + minPriceOffer);
        int index = minPriceOffer.getId();

        return $(xpath("(//*[contains(@class,'price-line-shop')]//*[contains(@class,'range-price')])["
                + index + "]/.."));
    }
}
