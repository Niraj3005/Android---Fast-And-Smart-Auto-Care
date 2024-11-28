package com.example.onroadremotevehicleassistantapp.Model;

public class AdminModel {
    int adminId;
    String adminFname,adminLname,adminEmail,adminPassword;

    public AdminModel(int adminId, String adminFname, String adminLname, String adminEmail, String adminPassword) {
        this.adminId = adminId;
        this.adminFname = adminFname;
        this.adminLname = adminLname;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    @Override
    public String toString() {
        return "AdminModel{" +
                "adminId=" + adminId +
                ", adminFname='" + adminFname + '\'' +
                ", adminLname='" + adminLname + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminFname() {
        return adminFname;
    }

    public void setAdminFname(String adminFname) {
        this.adminFname = adminFname;
    }

    public String getAdminLname() {
        return adminLname;
    }

    public void setAdminLname(String adminLname) {
        this.adminLname = adminLname;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
