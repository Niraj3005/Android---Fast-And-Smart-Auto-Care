package com.example.onroadremotevehicleassistantapp.Model;

import android.graphics.Bitmap;

public class ProductModel {
    private int productId,stockQuantity;
    private float productRating;
    private String productName,productCategory,vehicleType,productPrice,productDescr;
    private Bitmap productImage;

    public ProductModel(int productId, float productRating, String productName, String productCategory, String vehicleType, String productPrice, String productDescr, int stockQuantity, Bitmap productImage) {
        this.productId = productId;
        this.productRating = productRating;
        this.productName = productName;
        this.productCategory = productCategory;
        this.vehicleType = vehicleType;
        this.productPrice = productPrice;
        this.productDescr = productDescr;
        this.stockQuantity = stockQuantity;
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "productId=" + productId +
                ", productRating=" + productRating +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", productPrice='" + productPrice + '\'' +
                ", productDescr='" + productDescr + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                ", productImage=" + productImage +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(int productRating) {
        this.productRating = productRating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescr() {
        return productDescr;
    }

    public void setProductDescr(String productDescr) {
        this.productDescr = productDescr;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Bitmap getProductImage() {
        return productImage;
    }

    public void setProductImage(Bitmap productImage) {
        this.productImage = productImage;
    }
}
