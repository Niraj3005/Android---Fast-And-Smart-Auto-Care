package com.example.onroadremotevehicleassistantapp.Model;

import android.graphics.Bitmap;

public class UserModel {
    private int id;
    private String fname,lname,email,pwd,vehicleno,phoneno,add;
    private Bitmap photo;

    public UserModel(int id, String fname, String lname, String email, String pwd, String vehicleno, String phoneno, String add, Bitmap photo) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pwd = pwd;
        this.vehicleno = vehicleno;
        this.phoneno = phoneno;
        this.add = add;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", vehicleno='" + vehicleno + '\'' +
                ", phoneno='" + phoneno + '\'' +
                ", add='" + add + '\'' +
                ", photo=" + photo +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
