package com.example.fyp.ObjectClass;

import java.util.Arrays;

public class Achievements {

    private int achievement_id, required;
    private String achievement_desc;
    private byte[] achievement_img;

    public Achievements(int achievement_id, int required, String achievement_desc, byte[] achievement_img){
        this.achievement_id = achievement_id;
        this.required = required;
        this.achievement_desc = achievement_desc;
        this.achievement_img = achievement_img;
    }

    public int getAchievement_id() { return achievement_id; }

    public void setAchievement_id(int achievement_id) { this.achievement_id = achievement_id; }

    public int getRequired() { return required; }

    public void setRequired(int required) { this.required = required; }

    public String getAchievement_desc() { return achievement_desc; }

    public void setAchievement_desc(String achievement_desc) { this.achievement_desc = achievement_desc; }

    public byte[] getAchievement_img() { return achievement_img; }

    public void setAchievement_img(byte[] achievement_img) { this.achievement_img = achievement_img; }

    @Override
    public String toString() {
        return "Achievements{" +
                "achievement_id=" + achievement_id +
                ", required ='" + required + '\'' +
                ", achievement_desc='" + achievement_desc + '\'' +
                ", achievement_img=" + Arrays.toString(achievement_img) +
                '}';
    }
}
