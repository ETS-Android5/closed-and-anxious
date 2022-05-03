package dk.itu.closed_and_anxious;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class CatView extends AndroidViewModel {
    public MutableLiveData<ArrayList<Playlist>> categories;

    public CatView(Application a) {
        super(a);
        categories = new MutableLiveData<>();
        categories.setValue(new ArrayList<Playlist>(plSetup(a)));
        Log.i("~~~~~~~~~~~~~~~~~~", "ViewModel setup with size: " + categories.getValue().size());
    }

    /**
     * Setup method, creates the Playlists and returns an ArrayList of them.
     * @param a Application
     * @return ArrayList of Playlists with name, description, image ref and empty track lists.
     */
    private ArrayList<Playlist> plSetup(Application a) {
        ArrayList<Playlist> list = new ArrayList<>();
        list.add(new Playlist("Noise",  a.getString(R.string.descr_noise), R.drawable.noise));
        list.add(new Playlist("Anxiety",  a.getString(R.string.anx_descr), R.drawable.anxious));
        list.add(new Playlist("Frustration",  a.getString(R.string.descr_frustration), R.drawable.frustration));
        return list;
    }

    /**
     * Populates one of the PlayLists with Tracks.
     * ONLY call the first time someone clicks on a category!
     *
     * @param position Position of the PlayList in the Category array 0 -> Noise; 1 -> Anxiety; 2 -> Frustration
     * @param tracks ArrayList<Track> coming down from the Database
     */
    private void populatePlaylist(int position, ArrayList<Track> tracks){
        Log.i("~~~~~~~~~~~~", "Populating PlayList for List" + position);
        ArrayList<Playlist> temp= categories.getValue();
        if (temp.get(position).getTrackList().size() > 0) {Log.i("~~~~~!!!!~~~~~", "Playlist already populated!"); return;}
        Log.i("~~~~~~~~~~~~", "Adding Values now...");
        temp.get(position).populateList(tracks);
        categories.setValue(temp);
        Log.i("~~~~~~~~~~~~", "Tracks for Playlist set");
    }

    /**
     *
     * @param position 0 -> Noise; 1 -> Anxiety; 2 -> Frustration
     * @return Will return empty Playlists if PlayList isn't instantiated.
     */
    public Playlist getPlaylist (int position) {
        // if the position is out of bounds, return null
        if (0 > position || 2 < position) {return null;}

        // let's check if there are already tracks in the playlist
        ArrayList<Track> playlistTracks = categories.getValue().get(position).getTrackList();
        if (playlistTracks.size() == 0) { // if there are none

            populatePlaylist(position, TracksDB.getValues(getCategoryName(position))); // pls populate
        }

        return (categories.getValue().get(position)); // and return the PlayList incl. ArrayList<Tracks> with stuff in it.
    }

    /**
     * Get the name of the Playlist/ Category from an integer 0-2.
     *
     * returns null if input exceeds the constraints!
     *
     * @param i Position of the Playlist in the categories Array: 0-> Noise, 1-> Anxity; 2-> Frustration
     * @return Category/ Playlist name String at position i of the categories list
     */
    private String getCategoryName (int i) {
        if (0 > i || i > 2) {return null;}
        return categories.getValue().get(i).getName();
    }




}
