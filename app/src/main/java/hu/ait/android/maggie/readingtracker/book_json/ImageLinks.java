package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/6/2015.
 */
public class ImageLinks {

    @Expose
    private String smallThumbnail;
    @Expose
    private String thumbnail;
    @Expose
    private String small;
    @Expose
    private String medium;
    @Expose
    private String large;
    @Expose
    private String extraLarge;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getExtraLarge() {
        return extraLarge;
    }

    public void setExtraLarge(String extraLarge) {
        this.extraLarge = extraLarge;
    }
}
