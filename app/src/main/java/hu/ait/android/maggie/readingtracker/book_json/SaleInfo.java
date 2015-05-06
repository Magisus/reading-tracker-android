package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/6/2015.
 */
public class SaleInfo {

    @Expose
    private String country;
    @Expose
    private String saleability;
    @Expose
    private Boolean isEbook;
    @Expose
    private String buyLink;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public Boolean getIsEbook() {
        return isEbook;
    }

    public void setIsEbook(Boolean isEbook) {
        this.isEbook = isEbook;
    }

    public String getBuyLink() {
        return buyLink;
    }

    public void setBuyLink(String buyLink) {
        this.buyLink = buyLink;
    }
}
