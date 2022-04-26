package dk.itu.closed_and_anxious;


import android.media.MediaPlayer;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MediaPlayerView extends ViewModel {
    MediaPlayer player;


    // ~~~~~~~~~~~ player controls ~~~~~~~~~~~~

    public void play(View v) {
        System.out.println("~~~~~~~~~~~~ STARTING: Start playing Track");
        // if player is not created
        if (player == null) {
            // create a player with given track
            player = MediaPlayer.create(v.getContext(), R.raw.hurrystress);
            // when the player has completed a track
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    // we call the stopPlayer to release mp resources
                    stopPlayer();
                }
            });
        }

        // start the player (no matter if just created or already exists)
        player.start();
        System.out.println("~~~~~~~~~~~~ SUCCESS: Started playing Track");


    }

    public void pause(View v) {
        System.out.println("~~~~~~~~~~~~ STARTING: Pausing Track");
        // if player exists
        if (player != null) {
            // pause the track of the player
            player.pause();
            System.out.println("~~~~~~~~~~~~ SUCCESS: Paused Track");
        }

    }

    public void stop(View v) {
        System.out.println("~~~~~~~~~~~~ Starting: Stopping Track");
        stopPlayer();
        System.out.println("~~~~~~~~~~~~ SUCCESS: Stopped Track");
    }

    /*
        Called when the media player is stopped and when the media player has finished a song.
     */
    public void stopPlayer() {
        // if player is created
        if (player != null) {
            // release its resources
            player.release();
            // set the player to null (so a new one can be created)
            player = null;
            // Toast stating that the player has stopped
            //Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }





    // ~~~~~~~~~~~~ player management ~~~~~~~~~~~~~~~

    /*
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
    */

}
