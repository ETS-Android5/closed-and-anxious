package dk.itu.closed_and_anxious;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.track_ui);

    }

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
        stopPlayer();
        System.out.println("~~~~~~~~~~~~ Starting: Stopping Track");

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


}