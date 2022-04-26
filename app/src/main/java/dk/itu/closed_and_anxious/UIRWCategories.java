package dk.itu.closed_and_anxious;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UIRWCategories#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UIRWCategories extends Fragment {

    // let's make an ArrayList of Categories for our RecycleView

    ArrayList<Category> categories;

    Category anxious = new Category("Anxiety", "@string/are_you_anxious", "@drawable/anxious");

    public UIRWCategories() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UIRWCategories.
     */
    // TODO: Rename and change types and number of parameters
    public static UIRWCategories newInstance(String param1, String param2) {
        UIRWCategories fragment = new UIRWCategories();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ui_rw_categories, container, false);
    }
}