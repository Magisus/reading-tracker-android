package hu.ait.android.maggie.readingtracker.books;

import com.orm.SugarRecord;

/**
 * Created by Magisus on 5/1/2015.
 */
public class Book extends SugarRecord<Book> {

    public enum Status {IN_PROGRESS, FINISHED, TO_READ }

    private String title;
    private String author;
    private String coverUrl;
    private Status status;

    public Book(){
        //default constructor for SugarORM
    }

    public Book(String title, String author, String coverUrl){
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
}
