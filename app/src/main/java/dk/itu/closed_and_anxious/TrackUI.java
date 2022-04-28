package dk.itu.closed_and_anxious;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class TrackUI extends Fragment {
    //GUI
    //Should have three buttons (and one image)
    private Button playBtn, pauseBtn, stopBtn;

    //Button backBtn

    // mediaplayer to connect to onClick methods
    MediaPlayerView mpv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.track_ui, container, false);


        mpv = new MediaPlayerView();

        //backBtn.setOnClick from Track to Playlist
        //Navigation.findNavController(view).navigate(R.id.action_playlistUI_to_trackUI);

        /**
         * Implementing methods from the OnClickListener interface.
         * The view is passed to its related MediaPlayerView method.
         */

        playBtn = v.findViewById(R.id.play_button);
        playBtn.setOnClickListener(view -> {
                mpv.play(view);
        });

        pauseBtn = v.findViewById(R.id.pause_button);
        pauseBtn.setOnClickListener(view -> {
            mpv.pause(view);
        });

        stopBtn = v.findViewById(R.id.stop_button);
        stopBtn.setOnClickListener(view -> {
            mpv.stop(view);
        });

        return v;
    }

    /**
     * Overrides the onStop() lifecycle method of this fragment.
     * Using the MediaPlayerView stopPlayer method resource are released as well.
     */

    @Override
    public void onStop() {
        super.onStop();
        mpv.stopPlayer();

    }
}
