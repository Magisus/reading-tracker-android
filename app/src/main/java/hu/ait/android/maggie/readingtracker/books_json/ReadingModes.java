package hu.ait.android.maggie.readingtracker.books_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/2/2015.
 */
public class ReadingModes {

    @Expose
    private Boolean text;
    @Expose
    private Boolean image;

    public Boolean getText() {
        return text;
    }

    public void setText(Boolean text) {
        this.text = text;
    }

    public Boolean getImage() {
        return image;
    }

    public void setImage(Boolean image) {
        this.image = image;
    }
}
