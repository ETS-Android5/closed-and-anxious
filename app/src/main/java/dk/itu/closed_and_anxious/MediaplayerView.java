package dk.itu.closed_and_anxious;


import android.media.MediaPlayer;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MediaplayerView extends ViewModel {
    MutableLiveData<MediaPlayer> player;

    /**
     * ATTENTION!!!
     * This class has not been tested whatsoever.
     * 
     */
    public MediaplayerView() {
        player = new MutableLiveData<>();
        // we are not setting a value, yet.
        // We want to only create a mediaplayer when it is going to be used
        // if the app is too in loading the UI_Track fragment, we can create one here.
        // (Though I don't think we will need to.)
    }

    // ~~~~~~~~~~~ player controls ~~~~~~~~~~~~

    public void play() {
        if (player.getValue() == null) return;
        System.out.println("~~~~~~~~~~~~ STARTING: Start playing Track");
        player.getValue().start();
        System.out.println("~~~~~~~~~~~~ SUCCESS: Started playing Track");

    }

    public void pause() {
        if (player.getValue() == null) return;
        System.out.println("~~~~~~~~~~~~ STARTING: Pausing Track");
        player.getValue().pause();
        System.out.println("~~~~~~~~~~~~ SUCCESS: Paused Track");
    }

    public void stop(){
        if (player.getValue() != null) {
            System.out.println("~~~~~~~~~~~~ Starting: Stopping Track");
            player.getValue().stop();
            System.out.println("~~~~~~~~~~~~ SUCCESS: Stopped Track");

        }
    }

    // ~~~~~~~~~~~~ player management ~~~~~~~~~~~~~~~

    public void destroyPlayer() {
        System.out.println("~~~~~~~~~~~~ STARTING: Destroy current Player");
        if (player.getValue() == null) return;
        player.getValue().release();
        System.out.println("~~~~~~~~~~~~ SUCCESS: Released Player");
        player.setValue(null);
        System.out.println("~~~~~~~~~~~~ SUCCESS: Nullified Player");
    }

    public void setTrack(String trackKey) {
        System.out.println("~~~~~~~~~~~~ STARTING: Setting Track in player");
        if (player.getValue() != null) destroyPlayer();
        switch (trackKey) {
            case "breathe":
                //player.setValue(new MediaPlayer().create(this, R.raw.filenamehere));
                break;
                // all other cases for all Tracks go here
            default:
                break;
        }
        System.out.println("~~~~~~~~~~~~ SUCCESS: Set Track to " + trackKey);
    }

}
