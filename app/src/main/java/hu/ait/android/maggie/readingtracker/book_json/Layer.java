package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/6/2015.
 */
public class Layer {

    @Expose
    private String layerId;
    @Expose
    private String volumeAnnotationsVersion;

    public String getLayerId() {
        return layerId;
    }

    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }

    public String getVolumeAnnotationsVersion() {
        return volumeAnnotationsVersion;
    }

    public void setVolumeAnnotationsVersion(String volumeAnnotationsVersion) {
        this.volumeAnnotationsVersion = volumeAnnotationsVersion;
    }
}
