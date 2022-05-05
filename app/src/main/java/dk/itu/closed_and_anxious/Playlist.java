package dk.itu.closed_and_anxious;

import java.util.ArrayList;

public class Playlist {
    // display name corresponding to a category
    private String name;
    private String description;
    private int imageKey;
    private ArrayList<Track> trackList;

    private final boolean DEBUG = false;

    public Playlist (String category, String descr, int imageKey){
        name = category;
        description = descr;
        this.imageKey = imageKey;
        trackList = new ArrayList<>();
    }

    public void populateList(ArrayList<Track> list) {
        if (trackList.size() > 0) {
            if (DEBUG) System.out.println("~~~~~~~~~~ DEBUG Playlist.populateList() ~~~~~~~~~~~~");
            if (DEBUG) System.out.println("~~~~~~~~~~ Tried to populate trackList, but was already populated.");
            return;
        } else {
            trackList.addAll(list);
        }
    }

    public String getName() {
        return name;
    }

    public int getImageKey() {
        return imageKey;
    }

    public ArrayList<Track> getTrackList() {
        if (trackList.size() == 0 && DEBUG) System.out.println("~~~~~~~~~~ DEBUG Playlist.getTrackList -> has size 0.");
        return trackList;
    }


    public String getDescription() { return description;
    }
}
