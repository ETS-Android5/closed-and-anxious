package dk.itu.closed_and_anxious;




import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dk.itu.closed_and_anxious.database.TrackDBSchema;


public class PlaylistUI extends Fragment {

        TextView cat_title;
        Playlist track_playlist;
        private CatView cat_view;
        private int PLAYLIST_POSITION;

    @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // let's get the viewmodel
            cat_view = new ViewModelProvider(this).get(CatView.class);
            PLAYLIST_POSITION = getArguments().getInt("playlistInt");
        Log.i("~~~~~~~~~~~~", "in PlaylistUI: gotten PlaylistInt from CategoriesUI: "+PLAYLIST_POSITION);

    }



        @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View v = inflater.inflate(R.layout.playlist_ui, container, false);
            cat_title= v.findViewById(R.id.cat_header);
            //DB = new ViewModelProvider(requireActivity()).get(ViewModel.class);

            track_playlist = cat_view.getPlaylist(PLAYLIST_POSITION);

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


