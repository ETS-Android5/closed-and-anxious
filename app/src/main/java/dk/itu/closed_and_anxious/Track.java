package dk.itu.closed_and_anxious;

public class Track {
    private int key;
    private String dName;
    private String description;

    private String category;

    private int imageID;

    public Track(int iKey, String iName, String iDescription, String iCategory, int iImage) {
        key = iKey;
        dName = iName;
        description = iDescription;
        category = iCategory;
        imageID = iImage;
    }

    // ~~~~~~~  Getter Methods  ~~~~~~

    public int getKey() {
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
