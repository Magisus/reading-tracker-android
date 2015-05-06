package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/6/2015.
 */
public class Dimensions {

    @Expose
    private String height;

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
