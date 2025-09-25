
package com.example.pcshop;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    public static List<Product> selectedProducts = new ArrayList<>();
    //list of product in the cart
    public static void addToCart(Product product) {
        if (!selectedProducts.contains(product)) {
            selectedProducts.add(product);
        }
    }

    public static void removeFromCart(Product product) {
        selectedProducts.remove(product);
    }

    public static double getTotalCost() {
        double total = 0;
        for (Product p : selectedProducts) {
            total += p.getPrice();
        }
        return total;
    }
    //calclate total cost


    //used by costActivity to feed data into the adapter
    public static List<GalleryItem> getAsGalleryItems() {
        List<GalleryItem> items = new ArrayList<>();
        for (Product p : selectedProducts) {
            items.add(new GalleryItem(p));
        }
        return items;
    }


    public static void clear() {
        selectedProducts.clear();
    }
}
