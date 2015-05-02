package hu.ait.android.maggie.readingtracker.books_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/2/2015.
 */
public class Epub {

    @Expose
    private Boolean isAvailable;
    @Expose
    private String acsTokenLink;

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }
}
