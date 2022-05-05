package dk.itu.closed_and_anxious;


import android.media.MediaPlayer;
import android.view.View;

import androidx.lifecycle.ViewModel;

public class Mediaplayer {
    MediaPlayer player;
    boolean stopped = false;
    private final boolean DEBUG = false;

    // ~~~~~~~~~~~ player controls ~~~~~~~~~~~~

    public void play(View v, int trackKey) {
        if (DEBUG) System.out.println("~~~~~~~~~~~~ STARTING: Start playing Track");
        // if player is not created
        if (player == null) {
            // create a player with given track
           setTrack(v, trackKey);
            // when the player has completed a track
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    // we call the stopPlayer to release mp resources
                    destroyPlayer();
                }
            });
        }

        // if player has been stopped
        if (stopped) {
            stopped = false;
        }
        // start the player (no matter if just created or already exists)
        player.start();
        if (DEBUG) System.out.println("~~~~~~~~~~~~ SUCCESS: Started playing Track");

    }

    public void pause(View v) {
        if (DEBUG) System.out.println("~~~~~~~~~~~~ STARTING: Pausing Track");
        // if player exists
        if (player != null) {
            // pause the track of the player
            player.pause();
            if (DEBUG) System.out.println("~~~~~~~~~~~~ SUCCESS: Paused Track");
        }
    }

    public void stop(View v) {
        if (player != null) {
            if (DEBUG) System.out.println("~~~~~~~~~~~~ Starting: Stopping Track");
            //stopPlayer();
            player.stop();
            // Prepares the player for playback, asynchronously. Placed here, since preparation produce delay.
            player.prepareAsync();
            stopped = true;
            if (DEBUG) System.out.println("~~~~~~~~~~~~ SUCCESS: Stopped Track");
        }
    }

    /*
        Called when the media player is stopped and when the media player has finished a song.
     */
    public void destroyPlayer() {
        // if player is created
        if (player != null) {
            // release its resources
            player.release();
            // set the player to null (so a new one can be created)
            player = null;
            // Toast stating that the player has stopped
            // Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    // ~~~~~~~~~~~~ player management ~~~~~~~~~~~~~~~

    public void setTrack(View v, int trackKey) {
        if (DEBUG) System.out.println("~~~~~~~~~~~~ STARTING: Setting Track in player");
        player = MediaPlayer.create(v.getContext(), trackKey);
        if (DEBUG) System.out.println("~~~~~~~~~~~~ SUCCESS: Set Track to " + trackKey);
        }

}





