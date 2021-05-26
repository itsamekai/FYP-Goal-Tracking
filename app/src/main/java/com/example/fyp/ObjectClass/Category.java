package com.example.fyp.ObjectClass;

import java.util.Arrays;

public class Category {

    private int category_id;
    private String category_name, category_desc;
    private byte[] image;

    public Category(String category_name, String category_desc, byte[] image) {
        this.category_name = category_name;
        this.category_desc = category_desc;
        this.image = image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_desc() {
        return category_desc;
    }

    public void setCategory_desc(String category_desc) {
        this.category_desc = category_desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", category_name='" + category_name + '\'' +
                ", category_desc='" + category_desc + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
