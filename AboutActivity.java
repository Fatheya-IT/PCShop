package com.example.pcshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void goHome(android.view.View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void goGallery(android.view.View view) {
        startActivity(new Intent(this, GalleryActivity.class));
    }

    public void goCart(android.view.View view) {
        startActivity(new Intent(this, CostActivity.class));
    }

    public void goAbout(android.view.View view){
        //already here
    }

    public void goConatct(android.view.View view){
        startActivity(new Intent(this, ContactActivity.class));
    }
}
