package hu.ait.android.maggie.readingtracker.books_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/2/2015.
 */
public class ImageLinks {

    @Expose
    private String smallThumbnail;
    @Expose
    private String thumbnail;

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
}
