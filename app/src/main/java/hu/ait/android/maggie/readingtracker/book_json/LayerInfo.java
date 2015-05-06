package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magisus on 5/6/2015.
 */
public class LayerInfo {

    @Expose
    private List<Layer> layers = new ArrayList<Layer>();

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }
}
