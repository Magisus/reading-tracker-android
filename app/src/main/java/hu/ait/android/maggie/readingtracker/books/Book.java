package hu.ait.android.maggie.readingtracker.books;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Magisus on 5/1/2015.
 */
public class Book extends SugarRecord<Book> implements Serializable {

    public enum Status {
        IN_PROGRESS(0), TO_READ(1), FINISHED(2);

        private int index;

        private Status(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static Status fromInt(int value) {
            for (Status p : Status.values()) {
                if (p.index == value) {
                    return p;
                }
            }
            return TO_READ;
        }
    }

    private String title;
    private String author;
    private String coverUrl;
    private Status status;
    private int pageCount;
    private String apiId;
    private String publicationYear;
    private long dateStarted;
    private long dateFinished;
    private String description;

    public Book() {
        //default constructor for SugarORM
    }

    public Book(String title, String author, String coverUrl) {
        this.title = title;
        this.author = author;
        this.coverUrl = coverUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String id) {
        this.apiId = id;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDateFinishedString() {
        if (dateFinished <= 0) {
            return "N/A";
        }
        return new SimpleDateFormat("d MMMMM, yyyy").format(new Date(dateFinished));
    }

    public long getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(long dateFinished) {
        this.dateFinished = dateFinished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateStartedString() {
        if (dateStarted <= 0) {
            return "N/A";
        }
        return new SimpleDateFormat("d MMMMM, yyyy").format(new Date(dateStarted));
    }

    public long getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(long dateStarted) {
        this.dateStarted = dateStarted;
    }
}
