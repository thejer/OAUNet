package ng.codeinn.oaunet.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "news")
public class News {

    @PrimaryKey(autoGenerate = true)
    private int newsId;

    private String newsTitle;

    private String newsHeader;

    private String newsLink;

    private String newsFulltext;

    private String newsDateCreated;

    private String newsHits;


    public News(int newsId, String newsTitle, String newsHeader, String newsLink, String newsFulltext, String newsDateCreated, String newsHits) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsHeader = newsHeader;
        this.newsLink = newsLink;
        this.newsFulltext = newsFulltext;
        this.newsDateCreated = newsDateCreated;
        this.newsHits = newsHits;
    }

    @Ignore
    public News(String newsTitle, String newsHeader, String newsLink, String newsFulltext, String newsDateCreated, String newsHits) {
        this.newsTitle = newsTitle;
        this.newsHeader = newsHeader;
        this.newsLink = newsLink;
        this.newsFulltext = newsFulltext;
        this.newsDateCreated = newsDateCreated;
        this.newsHits = newsHits;
    }

    public int getNewsId() {
        return newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsHeader() {
        return newsHeader;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public String getNewsFulltext() {
        return newsFulltext;
    }

    public String getNewsDateCreated() {
        return newsDateCreated;
    }

    public String getNewsHits() {
        return newsHits;
    }
}
