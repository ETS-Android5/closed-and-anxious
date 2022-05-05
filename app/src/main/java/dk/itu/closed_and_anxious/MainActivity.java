package dk.itu.closed_and_anxious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //DB
    private static TracksDB tDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tDatabase = new TracksDB();
        setContentView(R.layout.activity_main);

        //Create database
        tDatabase.initialize(this);



        //FragmentManager fm = getSupportFragmentManager();

      // Fragment trackUI = fm.findFragmentById(R.id.container_ui);
        //Fragment categoriesUI = fm.findFragmentById(R.id.container_ui);
        //Fragment playlistUI = fm.findFragmentById(R.id.container_ui);

        //playlistUI = new PlaylistUI();
        // trackUI = new TrackUI();
        //categoriesUI = new UIRWCategories();
        // playlistUI = new PlaylistUI();

        //fm.beginTransaction().add(R.id.container_ui, trackUI).commit();
        //fm.beginTransaction().add(R.id.container_ui, categoriesUI).commit();
        //fm.beginTransaction().add(R.id.container_ui, playlistuI).commit();



    }

}