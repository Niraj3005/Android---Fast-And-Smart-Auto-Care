package com.example.onroadremotevehicleassistantapp.Model;

import android.graphics.Bitmap;

public class CartModel {
    private int cartId,userId,proId,quantity;
    private String proName,proPrice;
    private Bitmap proImg;

    public CartModel(int cartId, int userId, int proId, int quantity, String proName, String proPrice, Bitmap proImg) {
        this.cartId = cartId;
        this.userId = userId;
        this.proId = proId;
        this.quantity = quantity;
        this.proName = proName;
        this.proPrice = proPrice;
        this.proImg = proImg;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", proId=" + proId +
                ", quantity=" + quantity +
                ", proName='" + proName + '\'' +
                ", proPrice='" + proPrice + '\'' +
                ", proImg=" + proImg +
                '}';
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public Bitmap getProImg() {
        return proImg;
    }

    public void setProImg(Bitmap proImg) {
        this.proImg = proImg;
    }
}
