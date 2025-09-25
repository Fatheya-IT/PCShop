package com.example.pcshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
    }


    public void openTwitter(View view) {
        openUrl("https://x.com/ScreenShop");
    }

    public void openFacebook(View view) {
        openUrl("https://www.facebook.com/ScreenShop");
    }

    public void openInstagram(View view) {
        openUrl("https://www.instagram.com/ScreenShop");
    }

    public void openEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                Uri.fromParts("mailto","support@screenshop.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Support Request");
        startActivity(Intent.createChooser(emailIntent, "Send Email"));
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    // Navigation
    public void goHome(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void goGallery(View view) {
        startActivity(new Intent(this, GalleryActivity.class));
    }

    public void goCart(View view) {
        startActivity(new Intent(this, CostActivity.class));
    }

    public void goAbout(View view){
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void goConatct(View view){
        // already here
    }
}
