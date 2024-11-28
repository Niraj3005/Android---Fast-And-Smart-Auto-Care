package com.example.onroadremotevehicleassistantapp.Model;

import android.graphics.Bitmap;

public class ServiceModel {
    private int id;
    private String sname, sdesc, scharges;
    private Bitmap spicture;

    public ServiceModel(int id, String sname, String sdesc, String scharges, Bitmap spicture) {
        this.id = id;
        this.sname = sname;
        this.sdesc = sdesc;
        this.scharges = scharges;
        this.spicture = spicture;
    }

    @Override
    public String toString() {
        return "ServiceModel{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", sdesc='" + sdesc + '\'' +
                ", scharges='" + scharges + '\'' +
                ", spicture=" + spicture +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }

    public String getScharges() {
        return scharges;
    }

    public void setScharges(String scharges) {
        this.scharges = scharges;
    }

    public Bitmap getSpicture() {
        return spicture;
    }

    public void setSpicture(Bitmap spicture) {
        this.spicture = spicture;
    }
}