package dk.itu.closed_and_anxious;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import dk.itu.closed_and_anxious.database.TrackBaseHelper;

public class MainActivity extends AppCompatActivity {
    //DB
    private final static TracksDB tDatabase = new TracksDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        //Fragment trackUI = fm.findFragmentById(R.id.container_ui);
        //Fragment categoriesUI = fm.findFragmentById(R.id.container_ui);
        Fragment playlistUI = fm.findFragmentById(R.id.container_ui);
        playlistUI = new PlaylistUI();
        fm.beginTransaction().add(R.id.container_ui, playlistUI).commit();

        //Create database
        tDatabase.initialize(this);
    }

}