package hu.ait.android.maggie.readingtracker.books_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/2/2015.
 */
public class IndustryIdentifier {

    @Expose
    private String type;
    @Expose
    private String identifier;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
