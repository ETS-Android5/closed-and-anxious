package dk.itu.closed_and_anxious;

public class Track {
    private String key;
    private String dName;
    private String description;

    private String category;

    private String imageID;

    public Track(String iKey, String iName, String iDescription, String iCategory, String iImage) {
        key = iKey;
        dName = iName;
        description = iDescription;
        category = iCategory;
        imageID = iImage;
    }

    // ~~~~~~~  Getter Methods  ~~~~~~

    public String getKey() {
        return key;
    }

    public String getdName() {
        return dName;
    }

    public String getDescription() {
        return description;
    }

    public int getImageID() {
        return imageID;
    }

    public String getCategory() {
        return category;
    }

}
