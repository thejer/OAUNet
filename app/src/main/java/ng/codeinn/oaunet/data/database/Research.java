package ng.codeinn.oaunet.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "research")
public class Research {
    @PrimaryKey(autoGenerate = true)
    private int researchId;

    private String researchTitle;

    private String researchHeader;

    private String researchLink;

    private String researchFulltext;

    private String researchDateCreated;

    private String researchHits;


    public Research(int researchId, String researchTitle, String researchHeader, String researchLink, String researchFulltext, String researchDateCreated, String researchHits) {
        this.researchId = researchId;
        this.researchTitle = researchTitle;
        this.researchHeader = researchHeader;
        this.researchLink = researchLink;
        this.researchFulltext = researchFulltext;
        this.researchDateCreated = researchDateCreated;
        this.researchHits = researchHits;
    }

    @Ignore
    public Research(String researchTitle, String researchHeader, String researchLink, String researchFulltext, String researchDateCreated, String researchHits) {
        this.researchTitle = researchTitle;
        this.researchHeader = researchHeader;
        this.researchLink = researchLink;
        this.researchFulltext = researchFulltext;
        this.researchDateCreated = researchDateCreated;
        this.researchHits = researchHits;
    }

    public int getResearchId() {
        return researchId;
    }

    public String getResearchTitle() {
        return researchTitle;
    }

    public String getResearchHeader() {
        return researchHeader;
    }

    public String getResearchLink() {
        return researchLink;
    }

    public String getResearchFulltext() {
        return researchFulltext;
    }

    public String getResearchDateCreated() {
        return researchDateCreated;
    }

    public String getResearchHits() {
        return researchHits;
    }
}
