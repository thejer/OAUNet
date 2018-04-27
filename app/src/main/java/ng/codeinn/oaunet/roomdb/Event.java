package ng.codeinn.oaunet.roomdb;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int eventId;

    private String eventTitle;

    private String eventHeader;

    private String eventLink;

    private String eventFulltext;

    private String eventDateCreated;

    private String eventHits;


    public Event(int eventId, String eventTitle, String eventHeader, String eventLink, String eventFulltext, String eventDateCreated, String eventHits) {
        this.eventId = eventId;
        this.eventTitle = eventTitle;
        this.eventHeader = eventHeader;
        this.eventLink = eventLink;
        this.eventFulltext = eventFulltext;
        this.eventDateCreated = eventDateCreated;
        this.eventHits = eventHits;
    }

    @Ignore
    public Event(String eventTitle, String eventHeader, String eventLink, String eventFulltext, String eventDateCreated, String eventHits) {
        this.eventTitle = eventTitle;
        this.eventHeader = eventHeader;
        this.eventLink = eventLink;
        this.eventFulltext = eventFulltext;
        this.eventDateCreated = eventDateCreated;
        this.eventHits = eventHits;
    }
}
