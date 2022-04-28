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
     * @param position Position of the PlayList in the Category array 0 -> Anxiety; 1 -> Noise; 2 -> Frustration
     * @param tracks ArrayList<Track> coming down from the Database
     */
    public void populatePlaylist(int position, ArrayList<Track> tracks){
        Log.i("~~~~~~~~~~~~", "Populating PlayList for List" + position);
        ArrayList<Playlist> temp= categories.getValue();
        if (temp.size() > 0) {Log.i("~~~~~!!!!~~~~~", "Playlist already populated!"); return;}
        Log.i("~~~~~~~~~~~~", "Adding Values now...");
        temp.get(position).populateList(tracks);
        categories.setValue(temp);
        Log.i("~~~~~~~~~~~~", "Tracks for Playlist set");
    }

    public MutableLiveData<ArrayList<Playlist>> getValue() { return categories; }

    /**
     *
     * @param position 0 -> Noise; 1 -> Anxiety; 2 -> Frustration
     * @return Will return empty Playlists if PlayList isn't instantiated.
     */
    public Playlist getPlaylist (int position) {
        return (categories.getValue().get(position));
    }

}
