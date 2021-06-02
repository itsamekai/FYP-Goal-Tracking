package com.example.fyp.ObjectClass;

import java.util.Arrays;
import java.util.Date;

public class Users {

    private int user_id;
    private String username;
    private String password;
    private String full_name;
    private String dob;
    private int phoneNo;
    private String UserRole;
    private String address;
    private byte[] image;
    private String about;

    public Users(String username, String password, String full_name, String dob, int phoneNo, String userRole, String address) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.UserRole = userRole;
        this.address = address;
        this.image = null;
        this.about = null;
    }

    public Users(String username, String password, String full_name, String dob, int phoneNo) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.dob = dob;
        this.phoneNo = phoneNo;
        this.address = "WHC";
        this.image = null;
        this.UserRole = "Admin";
    }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", full_name='" + full_name + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNo=" + phoneNo +
                ", UserRole='" + UserRole + '\'' +
                ", address='" + address + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }


}
