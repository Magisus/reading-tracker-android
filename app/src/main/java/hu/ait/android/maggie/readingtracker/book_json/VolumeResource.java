package hu.ait.android.maggie.readingtracker.book_json;

import com.google.gson.annotations.Expose;

import hu.ait.android.maggie.readingtracker.book_search_json.AccessInfo;

/**
 * Created by Magisus on 5/6/2015.
 */
public class VolumeResource {
    @Expose
    private String kind;
    @Expose
    private String id;
    @Expose
    private String etag;
    @Expose
    private String selfLink;
    @Expose
    private VolumeInfo volumeInfo;
    @Expose
    private LayerInfo layerInfo;
    @Expose
    private SaleInfo saleInfo;
    @Expose
    private AccessInfo accessInfo;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public LayerInfo getLayerInfo() {
        return layerInfo;
    }

    public void setLayerInfo(LayerInfo layerInfo) {
        this.layerInfo = layerInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }
}
