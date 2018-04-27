package ng.codeinn.oaunet.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemModel {


    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("introtext")
    @Expose
    private String introtext;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("fulltext")
    @Expose
    private String fulltext;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("modified")
    @Expose
    private String modified;

    @Expose
    private String image;
    @SerializedName("imageWidth")
    @Expose
    private String imageWidth;
    @SerializedName("image_caption")
    @Expose
    private String imageCaption;
    @SerializedName("image_credits")
    @Expose
    private String imageCredits;
    @SerializedName("imageXSmall")
    @Expose
    private String imageXSmall;
    @SerializedName("imageSmall")
    @Expose
    private String imageSmall;
    @SerializedName("imageMedium")
    @Expose
    private String imageMedium;
    @SerializedName("imageLarge")
    @Expose
    private String imageLarge;
    @SerializedName("imageXLarge")
    @Expose
    private String imageXLarge;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("video_caption")
    @Expose
    private String videoCaption;
    @SerializedName("video_credits")
    @Expose
    private String videoCredits;
    @SerializedName("gallery")
    @Expose
    private Object gallery;
    @SerializedName("hits")
    @Expose
    private String hits;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIntrotext() {
        return introtext;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }


    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImageCredits() {
        return imageCredits;
    }

    public void setImageCredits(String imageCredits) {
        this.imageCredits = imageCredits;
    }

    public String getImageXSmall() {
        return imageXSmall;
    }

    public void setImageXSmall(String imageXSmall) {
        this.imageXSmall = imageXSmall;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getImageXLarge() {
        return imageXLarge;
    }

    public void setImageXLarge(String imageXLarge) {
        this.imageXLarge = imageXLarge;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideoCaption() {
        return videoCaption;
    }

    public void setVideoCaption(String videoCaption) {
        this.videoCaption = videoCaption;
    }

    public String getVideoCredits() {
        return videoCredits;
    }

    public void setVideoCredits(String videoCredits) {
        this.videoCredits = videoCredits;
    }

    public Object getGallery() {
        return gallery;
    }

    public void setGallery(Object gallery) {
        this.gallery = gallery;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }
}

