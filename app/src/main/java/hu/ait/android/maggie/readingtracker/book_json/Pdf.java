package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/6/2015.
 */
public class Pdf {

    @Expose
    private Boolean isAvailable;
    @Expose
    private String downloadLink;

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}
