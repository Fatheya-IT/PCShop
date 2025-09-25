package com.example.pcshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {


    private static final int DB_VERSION = 3;

    public DB(Context context) {
        super(context, "PCSTOREDB", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "category TEXT, " +
                "description TEXT, " +
                "price REAL, " +
                "imageID INTEGER)");


        insertProduct(db, "Apple MacBook Pro Retina XDR", "Laptop",
                "Apple Intelligence, 14.2, M3 Max36 GB RAM, 1 TB SSD, macOS Sonoma, 30 Core GPU",
                11499, R.drawable.mac);

        insertProduct(db, "Apple iMac Desktop", "Desktop",
                "Apple Intelligence, 24, M3, 8 GB RAM, 256 GB SSD",
                4899, R.drawable.imac);

        insertProduct(db, "Canon EOS 250D DSLR Camera", "Camera",
                "24.1 MP, CMOS, 4K UHD 3840 X 2160, with 18 - 55 mm NIS Lens",
                3079, R.drawable.cannon);

        insertProduct(db, "LG UltraGear 27 Gaming Monitor", "Monitor",
                "OLED, QHD (Quad HD), 240 Hz, 0.03ms (GtG), Black",
                3449, R.drawable.lg);

        insertProduct(db, "LG UltraGear 31.5 Gaming Monitor", "Monitor",
                "LED, QHD (Quad HD), 180 Hz, 1ms (GtG), Black",
                1299, R.drawable.lgt);
        insertProduct(db, "Samsung Galaxy S24 FE 5G", "SmartPhone",
                "256 GB,8 GB RAM, Mint, 5G, Exynos 2400e",
                1999, R.drawable.smartphone);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }


    private void insertProduct(SQLiteDatabase db, String name, String category, String desc, double price, int imageID) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("category", category);
        values.put("description", desc);
        values.put("price", price);
        values.put("imageID", imageID);
        db.insert("products", null, values);
    }


    // Reads all rows into a list of Product objects
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM products", null);

        while (c.moveToNext()) {
            list.add(new Product(
                    c.getString(1), // name
                    c.getString(2), // category
                    c.getString(3), // description
                    c.getDouble(4), // price
                    c.getInt(5)     // imageID
            ));
        }

        c.close();
        return list;
    }
}
