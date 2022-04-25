package dk.itu.closed_and_anxious;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment trackUI = fm.findFragmentById(R.id.container_ui);
        trackUI = new TrackUI();

        fm.beginTransaction().add(R.id.container_ui, trackUI).commit();
    }

}



