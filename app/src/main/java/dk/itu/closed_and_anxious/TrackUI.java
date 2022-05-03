package dk.itu.closed_and_anxious;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class TrackUI extends Fragment {
    //GUI
    //Should have three buttons (and one image)
    private ImageView trackImg, playBtn, pauseBtn, stopBtn;
    private TextView titleText;

    // mediaplayer to connect to onClick methods
    MediaPlayerView mpv;
    Track t1;
    Track t2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.track_ui, container, false);

        mpv = new MediaPlayerView();

        // for testing
        t1 = new Track(R.raw.hurrystress, "hurrystress", "Just relaaaaaax", "stress", R.drawable.noise);
        t2 = new Track(R.raw.breathe, "breath", "Just breaaaaaath", "anxious", R.drawable.anxious);

        /**
         * Implementing methods from the OnClickListener interface.
         * Each ImageView representing a player button has been made clickable,
         * by using the onClick attribute in its layout and assigning each their related method (see corresponding XML).
         */

        titleText = v.findViewById(R.id.trackTitle);
        titleText.setText(t2.getdName());

        trackImg =  v.findViewById(R.id.trackImage);
        trackImg.setImageResource(t2.getImageID());

        playBtn = v.findViewById(R.id.play_button);
        playBtn.setOnClickListener(view -> {
                mpv.play(view, t2.getKey());
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
     * Overrides the onDestroy() lifecycle method of this fragment.
     * Using the MediaPlayerView destroyPlayer method resource are released as well.
     */

    @Override
    public void onDestroy() {
        super.onDestroy();
        mpv.destroyPlayer();
    }
}
