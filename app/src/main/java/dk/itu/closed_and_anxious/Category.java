package dk.itu.closed_and_anxious;

public class Category {
    private String dName;
    private String description;
    private String imageKey;

    private Playlist playlist;

    public Category (String name, String descr, String image) {
        dName = name;
        description = descr;
        imageKey = image;
        playlist = new Playlist(dName, imageKey);
    }

    public String getdName() {
        return dName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageKey() {
        return imageKey;
    }

    public Playlist getPlaylist() {
        return playlist;
    }
}
