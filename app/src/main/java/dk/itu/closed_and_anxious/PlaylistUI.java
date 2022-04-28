package dk.itu.closed_and_anxious;




import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PlaylistUI extends Fragment {

        TextView cat_title;
        Playlist track_playlist;

        private CatView cat_view = new ViewModelProvider(this).get(CatView.class);

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }
        @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View v = inflater.inflate(R.layout.playlist_ui, container, false);
            cat_title= v.findViewById(R.id.cat_header);
            //DB = new ViewModelProvider(requireActivity()).get(ViewModel.class);

            // ~~~~~~~~~~~~~~~~~~~~~~ This is for Recycleview Testing! ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            track_playlist = new Playlist("Noise",getString(R.string.descr_noise), R.drawable.noise);
            ArrayList<Track> temp = new ArrayList<>();
            // public Track(int iKey, String iName, String iDescription, String iCategory, int iImage)
            temp.add(new Track(R.raw.gently, "Gently Does It","An atmospheric, uplifting soundscape. Credit: Ketsa", "Noise", R.drawable.cover_coffee1));
            temp.add(new Track(R.raw.life, "Choose Life", "An atmospheric lofi-tune with true dead-battery vibes. Credit: Daniel Birch", "Noise", R.drawable.cover_ice_1));
            temp.add(new Track(R.raw.adrift15, "Adrift No. 15", "A cosmic string set to invoke wideness, wonder and calm. Credit: Daniel Birch", "Noise", R.drawable.cover_plants_3));
            temp.add(new Track(R.raw.dark, "Dark Water", "A mystical, layered soundscape to calm and stimulate overworked minds. Credit: Nul Tiel Records", "Noise", R.drawable.cover_roses_2));
            temp.add(new Track(R.raw.quad, "foldable quadcopter", "A deep, atmospheric electronic track for perspective and meditation. Credit: bibby", "Noise", R.drawable.cover_darksand_4));
            temp.add(new Track(R.raw.last, "Last Light", "A simple compounding soundscape to meditate to. Credit: Nul Tiel Records", "Noise", R.drawable.cover_ice_4));
            temp.add(new Track(R.raw.time, "Too Brief A Time To Be Anything", "A 45-minute cosmic meditation soundscape to settle your mind or drift asleep to. Credit: HoliznaCC0", "Noise", R.drawable.cover_ice_3));
            temp.add(new Track(R.raw.waves, "Cosmic Waves", "A 33-minute deep meditation soundscape with calming, futuristic sounds. Credit: HoliznaCC0", "Noise", R.drawable.cover_glow_2));
            temp.add(new Track(R.raw.amb1, "Ambience", "A well-rounded, focus track for attentive listening-meditations or focused work. Credit: Independent Music Licensing Collective", "Noise", R.drawable.cover_darksand_2));
            temp.add(new Track(R.raw.amblo, "Ambience 7", "Classic complex soundscape for mellow focus. Credit: Independent Music Licensing Collective", "Noise", R.drawable.cover_palm_1));

            track_playlist.populateList(temp);

            // release the ArrayList
            temp.clear();

            // Set Playlist Title
            cat_title.setText(track_playlist.getName());

            // ~~~~~~~~~~~~~~~~ Setting up for Landscape view ~~~~~~~~~~~~~~~~~~
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // set description
                TextView descr = v.findViewById(R.id.cat_descr);
                descr.setText(track_playlist.getDescription());

                ImageView img = v.findViewById(R.id.cat_img);
                img.setImageResource(track_playlist.getImageKey());
            }

            // Set up recyclerview
            RecyclerView playList = v.findViewById(R.id.rv_playList);
            playList.setLayoutManager(new LinearLayoutManager(getActivity()));
            TrackAdapter tAdapter = new TrackAdapter();
            playList.setAdapter(tAdapter);

            //itemsDB.getValue().observe(getActivity(), itemsDB -> mAdapter.notifyDataSetChanged());
            return v;
        }

        private class TrackHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private final TextView tck_titleTv,  tck_dscrTv;
            private final ImageView imageV;

            public TrackHolder(View trackView) {
                super(trackView);
                tck_titleTv= trackView.findViewById(R.id.tck_header);
                imageV= trackView.findViewById(R.id.img_anx);
                tck_dscrTv= trackView.findViewById(R.id.anx_descr);
                trackView.setOnClickListener(this);
            }

            public void bind(Track track, int position){

                tck_titleTv.setText(track.getdName());
                tck_dscrTv.setText(track.getDescription());
                imageV.setImageResource(track.getImageID());
            }

            @Override
            public void onClick(View view) {

            }
        }
        private class TrackAdapter extends RecyclerView.Adapter<TrackHolder> {

            @Override
            public TrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                View v = layoutInflater.inflate(R.layout.track_row, parent, false);
                return new TrackHolder(v);
            }

            @Override
            public void onBindViewHolder(TrackHolder holder, int position) {
                Track track = track_playlist.getTrackList().get(position);
                holder.bind(track, position);
            }

            @Override
            public int getItemCount() {
                return track_playlist.getTrackList().size();
            }

        }
    }


