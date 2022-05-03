package dk.itu.closed_and_anxious;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class UIRWCategories extends Fragment {

    // let's make an ArrayList of PlayList for our RecycleView
    private CatView cat_view;

    public UIRWCategories() {
        // Required empty public constructor
    }

    public static UIRWCategories newInstance() {
        UIRWCategories fragment = new UIRWCategories();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cat_view = new ViewModelProvider(this).get(CatView.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.ui_rw_categories, container, false);

        // let's set up the RecyclerView
        RecyclerView catList = v.findViewById(R.id.cat_recyclerView);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            catList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        } else {
            catList.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        CategoryAdapter mAdapter = new CategoryAdapter();
        catList.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return v;

    }


    private class CategoryHolder extends RecyclerView.ViewHolder {
        private final TextView header, description;
        private final ImageView img;

        public CategoryHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.cat_header);
            description = itemView.findViewById(R.id.cat_descr);
            img = itemView.findViewById(R.id.cat_img);
        }

        public void bind(Playlist cat, int position) {
            header.setText(cat.getName());
            description.setText(cat.getDescription());
            img.setImageResource(cat.getImageKey());
        }

    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {

        @Override
        public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(requireContext());
            View v = layoutInflater.inflate(R.layout.cat_row, parent, false);
            return new CategoryHolder(v);
        }

        @Override
        public void onBindViewHolder(CategoryHolder holder, int position) {
            Playlist cat = cat_view.getCategories().getValue().get(position);
            holder.bind(cat, position);
            holder.itemView.setOnClickListener((v -> {
                Log.i("~~~~~~~~~~~~", "in RecyclerView onClick: Starting Click");
                // here goes the function that navigates to the PlayList function
                // and passes it the int position of the category

                // we are using SafeArgs here. SafeArgs creates a Class of "FragmentName + Directions" for each Fragment in the nav_graph
                // here UIRWCategoresDirections

                // and also a class for each navigation action, named after their nav_graph name, all caps
                // we are saving an instance of the 'action'-method call as variable 'action',
                // becaues it is part of the safeArgs navigation with content passing.
                UIRWCategoriesDirections.ActionUIRWCategoriesToPlaylistUI action = UIRWCategoriesDirections.actionUIRWCategoriesToPlaylistUI();

                Log.i("~~~~~~~~~~~~", "in RecyclerView onClick: Create Action");

                // the library will also auto-generate a 'setArgument(here)'-method.
                // we use this to pass the action the information we want to pass.
                action.setPlaylistInt(position);


                Log.i("~~~~~~~~~~~~", "in RecyclerView onClick: Set the PlaylistInt");
                Log.i("~~~~~~~~~~~~", "in RecyclerView onClick: currentPosition is " + position);

                // Then we just pass this action to the navigate()-method
                // held by the NavControler within the Navigation class
                Log.i("~~~~~~~~~~~~", "in RecyclerView onClick: Starting navigation...");
                Navigation.findNavController(v).navigate(action);

            }));
        }

        @Override
        public int getItemCount() {
            return cat_view.getCategories().getValue().size();
        }
    }

}