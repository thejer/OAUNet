package ng.codeinn.oaunet.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "item", indices = @Index(value = {"itemLink"}, unique = true))
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int itemId;

    private String itemTitle;

    private String itemHeader;

    private String itemLink;

    private String itemFulltext;

    private String itemDateCreated;

    private String itemHits;

    private int itemType;

    public Item(int itemId, String itemTitle, String itemHeader, String itemLink, String itemFulltext, String itemDateCreated, String itemHits, int itemType) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemHeader = itemHeader;
        this.itemLink = itemLink;
        this.itemFulltext = itemFulltext;
        this.itemDateCreated = itemDateCreated;
        this.itemHits = itemHits;
        this.itemType = itemType;
    }

    @Ignore
    public Item(String itemTitle, String itemHeader, String itemLink, String itemFulltext, String itemDateCreated, String itemHits, int itemType) {
        this.itemTitle = itemTitle;
        this.itemHeader = itemHeader;
        this.itemLink = itemLink;
        this.itemFulltext = itemFulltext;
        this.itemDateCreated = itemDateCreated;
        this.itemHits = itemHits;
        this.itemType = itemType;
    }

    public int getItemType() {
        return itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemHeader() {
        return itemHeader;
    }

    public String getItemLink() {
        return itemLink;
    }

    public String getItemFulltext() {
        return itemFulltext;
    }

    public String getItemDateCreated() {
        return itemDateCreated;
    }

    public String getItemHits() {
        return itemHits;
    }
}
