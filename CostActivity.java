package com.example.pcshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CostActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    ProductAdapter adapter;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cost);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        total = findViewById(R.id.Total);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //get all products in the cart wrapped as galleryitems type product only
        List<GalleryItem> displayList = Cart.getAsGalleryItems();

        //true = cart mode
        adapter = new ProductAdapter(this, displayList, true); // true = cart mode
        cartRecyclerView.setAdapter(adapter);

        updateTotal();
    }

    //sum the price of the products and update the lable with the total
    public void updateTotal() {
        double sum = Cart.getTotalCost();
        total.setText("Total: $" + String.format("%.2f", sum));
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        updateTotal();
    }

    public void goHome(android.view.View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void goGallery(android.view.View view) {
        startActivity(new Intent(this, GalleryActivity.class));
    }

    public void goCart(android.view.View view) {
        // Already here
    }

    public void goAbout(android.view.View view){
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void goConatct(android.view.View view){
        startActivity(new Intent(this, ContactActivity.class));
    }
}
