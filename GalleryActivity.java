
package com.example.pcshop;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GalleryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        //the view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //to retrieves from the database
        db = new DB(this);
        List<Product> rawProducts = db.getAllProducts();

        //Group products by catagory
        Map<String, List<Product>> grouped = new TreeMap<>();
        for (Product p : rawProducts) {
            grouped.computeIfAbsent(p.getCategory(), k -> new ArrayList<>()).add(p);
        }

        //Converts each category into a GalleryItem with type TYPE_HEADER,
        // and each product into a GalleryItem with type TYPE_PRODUCT
        List<GalleryItem> displayList = new ArrayList<>();
        for (String category : grouped.keySet()) {
            displayList.add(new GalleryItem(category));
            for (Product p : grouped.get(category)) {
                displayList.add(new GalleryItem(p));
            }
        }

        //false gallery mode list
        adapter = new ProductAdapter(this, displayList, false); // false = not cart mode
        recyclerView.setAdapter(adapter);

    }

    public void goHome(android.view.View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void goGallery(android.view.View view) { }

    public void goCart(android.view.View view) {
        startActivity(new Intent(this, CostActivity.class));
    }

    public void goAbout(android.view.View view) {
        startActivity(new Intent(this, AboutActivity.class));
    }

    public void goConatct(android.view.View view) {
        startActivity(new Intent(this, ContactActivity.class));
    }
}
