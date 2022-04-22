package dk.itu.closed_and_anxious;

import java.util.ArrayList;

public class Playlist {
    // display name corresponding to a category
    private String name;
    private String imageKey;
    private ArrayList<Track> trackList;

    public Playlist (String category, String imageKey){
        name = category;
        this.imageKey = imageKey;
        trackList = new ArrayList<>();
    }

    public void populateList(ArrayList<Track> list) {
        if (trackList.size() > 0) {
            System.out.println("~~~~~~~~~~ DEBUG Playlist.populateList() ~~~~~~~~~~~~");
            System.out.println("~~~~~~~~~~ Tried to populate trackList, but was already populated.");
            return;
        }
        trackList.addAll(list);
    }

    public String getName() {
        return name;
    }

    public String getImageKey() {
        return imageKey;
    }

    public ArrayList<Track> getTrackList() {
        if (trackList.size() > 0) System.out.println("~~~~~~~~~~ DEBUG Playlist.getTrackList -> has size 0.");
        return trackList;
    }
}
