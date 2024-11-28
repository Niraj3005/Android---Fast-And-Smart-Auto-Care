package com.example.onroadremotevehicleassistantapp.Model;

import android.graphics.Bitmap;

import java.util.List;

public class OrderModel {
    private int orderId,ordercartId,orderuserId,orderproId,orderStatus,orderQuantity;
    private String orderDate,orderPrice,orderproductName,orderDeliveryAddress,modeOfPayment;
    private Bitmap orderproductPhoto;
    private float orderproductRating;

    public OrderModel(int orderId, int ordercartId, int orderuserId, int orderproId, String orderDate, String orderPrice, String orderproductName, String orderDeliveryAddress, String modeOfPayment, int orderStatus, int orderQuantity, Bitmap orderproductPhoto, float orderproductRating) {
        this.orderId = orderId;
        this.ordercartId = ordercartId;
        this.orderuserId = orderuserId;
        this.orderproId = orderproId;
        this.orderStatus = orderStatus;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.orderproductName = orderproductName;
        this.orderDeliveryAddress = orderDeliveryAddress;
        this.modeOfPayment = modeOfPayment;
        this.orderproductPhoto = orderproductPhoto;
        this.orderproductRating = orderproductRating;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId=" + orderId +
                ", ordercartId=" + ordercartId +
                ", orderuserId=" + orderuserId +
                ", orderproId=" + orderproId +
                ", orderStatus=" + orderStatus +
                ", orderQuantity=" + orderQuantity +
                ", orderDate='" + orderDate + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", orderproductName='" + orderproductName + '\'' +
                ", orderDeliveryAddress='" + orderDeliveryAddress + '\'' +
                ", modeOfPayment='" + modeOfPayment + '\'' +
                ", orderproductPhoto=" + orderproductPhoto +
                ", orderproductRating=" + orderproductRating +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrdercartId() {
        return ordercartId;
    }

    public void setOrdercartId(int ordercartId) {
        this.ordercartId = ordercartId;
    }

    public int getOrderuserId() {
        return orderuserId;
    }

    public void setOrderuserId(int orderuserId) {
        this.orderuserId = orderuserId;
    }

    public int getOrderproId() {
        return orderproId;
    }

    public void setOrderproId(int orderproId) {
        this.orderproId = orderproId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderproductName() {
        return orderproductName;
    }

    public void setOrderproductName(String orderproductName) {
        this.orderproductName = orderproductName;
    }

    public String getOrderDeliveryAddress() {
        return orderDeliveryAddress;
    }

    public void setOrderDeliveryAddress(String orderDeliveryAddress) {
        this.orderDeliveryAddress = orderDeliveryAddress;
    }

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Bitmap getOrderproductPhoto() {
        return orderproductPhoto;
    }

    public void setOrderproductPhoto(Bitmap orderproductPhoto) {
        this.orderproductPhoto = orderproductPhoto;
    }

    public float getOrderproductRating() {
        return orderproductRating;
    }

    public void setOrderproductRating(float orderproductRating) {
        this.orderproductRating = orderproductRating;
    }
}
