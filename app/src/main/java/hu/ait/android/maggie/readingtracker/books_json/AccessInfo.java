package hu.ait.android.maggie.readingtracker.books_json;

import com.google.gson.annotations.Expose;

/**
 * Created by Magisus on 5/2/2015.
 */
public class AccessInfo {

    @Expose
    private String country;
    @Expose
    private String viewability;
    @Expose
    private Boolean embeddable;
    @Expose
    private Boolean publicDomain;
    @Expose
    private String textToSpeechPermission;
    @Expose
    private Epub epub;
    @Expose
    private Pdf pdf;
    @Expose
    private String webReaderLink;
    @Expose
    private String accessViewStatus;
    @Expose
    private Boolean quoteSharingAllowed;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getViewability() {
        return viewability;
    }

    public void setViewability(String viewability) {
        this.viewability = viewability;
    }

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    public Boolean getPublicDomain() {
        return publicDomain;
    }

    public void setPublicDomain(Boolean publicDomain) {
        this.publicDomain = publicDomain;
    }

    public String getTextToSpeechPermission() {
        return textToSpeechPermission;
    }

    public void setTextToSpeechPermission(String textToSpeechPermission) {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    public Epub getEpub() {
        return epub;
    }

    public void setEpub(Epub epub) {
        this.epub = epub;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public String getAccessViewStatus() {
        return accessViewStatus;
    }

    public void setAccessViewStatus(String accessViewStatus) {
        this.accessViewStatus = accessViewStatus;
    }

    public Boolean getQuoteSharingAllowed() {
        return quoteSharingAllowed;
    }

    public void setQuoteSharingAllowed(Boolean quoteSharingAllowed) {
        this.quoteSharingAllowed = quoteSharingAllowed;
    }

}
