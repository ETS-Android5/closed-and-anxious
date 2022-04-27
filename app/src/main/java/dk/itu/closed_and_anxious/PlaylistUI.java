package dk.itu.closed_and_anxious;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PlaylistUI extends Fragment {

    TextView cat_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.playlist_ui, container, false);
        cat_title= v.findViewById(R.id.cat_header);
        //DB = new ViewModelProvider(requireActivity()).get(ViewModel.class);

        // Set up recyclerview
        RecyclerView playList = v.findViewById(R.id.rv_playList);
        playList.setLayoutManager(new LinearLayoutManager(getActivity()));
        ItemAdapter mAdapter = new ItemAdapter();
        playList.setAdapter(mAdapter);

        //itemsDB.getValue().observe(getActivity(), itemsDB -> mAdapter.notifyDataSetChanged());


        return v;
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tck_titleTv, imageV,  tck_descrTv;

        public ItemHolder(View itemView) {
            super(itemView);
            tck_titleTv= itemView.findViewById(R.id.tck_header);
            imageV= itemView.findViewById(R.id.img_anx);
            tck_descrTv= itemView.findViewById(R.id.anx_descr);
            itemView.setOnClickListener(this);
        }

        public void bind(Track track, int position){
            imageV.setText(" "+position+" ");
            tck_titleTv.setText(track.getdName());
            tck_descrTv.setText(track.getDescription());
        }

    }
    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.track_row, parent, false);
            return new ItemHolder(v);
        }

       // @Override
       // public void onBindViewHolder(ItemHolder holder, int position) {
         //   Item item = itemsDB.getList().get(position);
          //  holder.bind(item, position);
       // }
        //counting how many things are on the list
       // @Override
      //  public int getItemCount() {
           // return itemsDB.size();
       // }

    }
}

