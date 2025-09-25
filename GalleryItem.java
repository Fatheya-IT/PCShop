package com.example.pcshop;

public class GalleryItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_PRODUCT = 1;

    private final int type;
    private final String headerText;
    private final Product product;

    public GalleryItem(String headerText) {
        this.type = TYPE_HEADER;
        this.headerText = headerText;
        this.product = null;
    }

    public GalleryItem(Product product) {
        this.type = TYPE_PRODUCT;
        this.headerText = null;
        this.product = product;
    }

    public int getType() { return type; }
    public String getHeaderText() { return headerText; }
    public Product getProduct() { return product; }
}