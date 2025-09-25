package com.example.pcshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnHome = findViewById(R.id.btnHome);


        btnHome.setOnClickListener(view -> startActivity(new Intent(this, HomeActivity.class)));

    }
}
