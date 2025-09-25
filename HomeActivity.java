package com.example.pcshop;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    public static MediaPlayer mediaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //play and loop the background music only once across all pages
        if (mediaP == null) {
            mediaP = MediaPlayer.create(this, R.raw.home_music);
            mediaP.setLooping(true);
            mediaP.start();
        }

        //button for the browes gallery
        Button startBtn = findViewById(R.id.btnStart);
        startBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        });

        //button to stop and start music
        Button musicToggleBtn = findViewById(R.id.btnToggleMusic);
        musicToggleBtn.setText(mediaP != null && mediaP.isPlaying() ? "Pause Music" : "Play Music");

        musicToggleBtn.setOnClickListener(v -> {
            if (mediaP != null) {
                if (mediaP.isPlaying()) {
                    mediaP.pause();
                    musicToggleBtn.setText("Play Music");
                } else {
                    mediaP.start();
                    musicToggleBtn.setText("Pause Music");
                }
            }
        });
    }

    //image-based navigation menu
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void goHome(android.view.View view) {
        // already on Home
    }

    public void goGallery(android.view.View view) {
        startActivity(new Intent(this, GalleryActivity.class));
    }

    public void goCart(android.view.View view) {
        startActivity(new Intent(this, CostActivity.class));
    }

    public void goAbout(android.view.View view){
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void goConatct(android.view.View view){
        startActivity(new Intent(this, ContactActivity.class));
    }

}
