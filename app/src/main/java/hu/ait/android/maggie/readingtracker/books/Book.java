package hu.ait.android.maggie.readingtracker.books;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Magisus on 5/1/2015.
 */
public class Book extends SugarRecord<Book> implements Serializable {

    public enum Status {
        IN_PROGRESS(0), TO_READ(1), FINISHED(2);

        private int index;

        private Status(int index){
            this.index = index;
        }

        public int getIndex(){
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
