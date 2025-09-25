
package com.example.pcshop;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<GalleryItem> itemList;
    boolean isCartMode;

    public ProductAdapter(Context context, List<GalleryItem> itemList, boolean isCartMode) {
        this.context = context;
        this.itemList = itemList;
        this.isCartMode = isCartMode;
    }

    // HEADER=0 or PRODUCT=0
    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == GalleryItem.TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
            return new ProductViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GalleryItem item = itemList.get(position);

        if (item.getType() == GalleryItem.TYPE_HEADER) {
            HeaderViewHolder hvh = (HeaderViewHolder) holder;
            hvh.headerText.setText(item.getHeaderText());
        } else {
            Product p = item.getProduct();
            ProductViewHolder vh = (ProductViewHolder) holder;
            vh.name.setText(p.getName());
            vh.image.setImageResource(p.getImageID());
            vh.price.setText("$" + p.getPrice());

            vh.itemView.setOnClickListener(v -> {
                new AlertDialog.Builder(context)
                        .setTitle(p.getName())
                        .setMessage("Category: " + p.getCategory()
                                + "\nPrice: $" + p.getPrice()
                                + "\n\n" + p.getDescription())
                        .setPositiveButton("OK", null)
                        .show();
            });
            //remove
            if (isCartMode) {
                vh.action.setText("Delete");
                vh.action.setOnClickListener(v -> {
                    Cart.removeFromCart(p);
                    itemList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, itemList.size());

                    if (context instanceof CostActivity) {
                        ((CostActivity) context).updateTotal();
                    }

                    Toast.makeText(context, p.getName() + " removed from cart", Toast.LENGTH_SHORT).show();
                });
            } else {
                //adds
                vh.action.setText("Add to Cart");
                vh.action.setOnClickListener(v -> {
                    if (!Cart.selectedProducts.contains(p)) {
                        Cart.addToCart(p);
                        Toast.makeText(context, p.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, p.getName() + " is already in the cart", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        Button action;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.productPrice);
            action = itemView.findViewById(R.id.btnAddToCart);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView headerText;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerText = itemView.findViewById(R.id.headerText);
        }
    }
}
