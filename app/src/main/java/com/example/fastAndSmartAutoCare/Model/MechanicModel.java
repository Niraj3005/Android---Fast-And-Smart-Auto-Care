package com.example.onroadremotevehicleassistantapp.Model;

import android.graphics.Bitmap;

public class MechanicModel {
    private int mid, isactive;
    private String mfname, mlname, memail, mpwd, mphoneno, maddress,regDate;
    private Bitmap mprofileimage;
    private float mrating;

    public MechanicModel(int mid, String mfname, String mlname, String memail, String mpwd, String mphoneno, String maddress, String regDate, Bitmap mprofileimage, int isactive, float mrating) {
        this.mid = mid;
        this.isactive = isactive;
        this.mfname = mfname;
        this.mlname = mlname;
        this.memail = memail;
        this.mpwd = mpwd;
        this.mphoneno = mphoneno;
        this.maddress = maddress;
        this.regDate = regDate;
        this.mprofileimage = mprofileimage;
        this.mrating = mrating;

    }

    @Override
    public String toString() {
        return "MechanicModel{" +
                "mid=" + mid +
                ", isactive=" + isactive +
                ", mfname='" + mfname + '\'' +
                ", mlname='" + mlname + '\'' +
                ", memail='" + memail + '\'' +
                ", mpwd='" + mpwd + '\'' +
                ", mphoneno='" + mphoneno + '\'' +
                ", maddress='" + maddress + '\'' +
                ", regDate='" + regDate + '\'' +
                ", mprofileimage=" + mprofileimage +
                ", mrating=" + mrating +
                '}';
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public String getMfname() {
        return mfname;
    }

    public void setMfname(String mfname) {
        this.mfname = mfname;
    }

    public String getMlname() {
        return mlname;
    }

    public void setMlname(String mlname) {
        this.mlname = mlname;
    }

    public String getMemail() {
        return memail;
    }

    public void setMemail(String memail) {
        this.memail = memail;
    }

    public String getMpwd() {
        return mpwd;
    }

    public void setMpwd(String mpwd) {
        this.mpwd = mpwd;
    }

    public String getMphoneno() {
        return mphoneno;
    }

    public void setMphoneno(String mphoneno) {
        this.mphoneno = mphoneno;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public Bitmap getMprofileimage() {
        return mprofileimage;
    }

    public void setMprofileimage(Bitmap mprofileimage) {
        this.mprofileimage = mprofileimage;
    }

    public float getMrating() {
        return mrating;
    }

    public void setMrating(float mrating) {
        this.mrating = mrating;
    }
}