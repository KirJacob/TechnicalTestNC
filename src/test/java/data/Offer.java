package data;

public class Offer {
    private String shopName;
    private int reviewsNumber;
    private int warranty;
    private int price;
    private int id;

    public Offer(int id, String shopName, int reviewsNumber, int warranty, int price){
        this.id = id;
        this.shopName = shopName;
        this.reviewsNumber = reviewsNumber;
        this.warranty = warranty;
        this.price = price;
    }

    public int getPrice(){
        return price;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        String result;
        result = "[ id=" + this.id + ", shopName=" + this.shopName + ", reviewsNumber=" + this.reviewsNumber
                + ", warranty=" + this.warranty + ", price=" + this.price + " ]";
        return result;
    }
}
