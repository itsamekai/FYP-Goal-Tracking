package com.example.fyp.ObjectClass;

import java.util.Arrays;

public class UserAchievement {

    private int user_achieved_id;
    private int achievement_id;
    private int user_id;
    private String datetime_achieved;

    public UserAchievement(int user_achieved_id, int achievement_id, int user_id, String datetime_achieved){
        this.user_achieved_id = user_achieved_id;
        this.achievement_id = achievement_id;
        this.user_id = user_id;
        this.datetime_achieved = datetime_achieved;
    }

    public int getUser_achieved_id() { return user_achieved_id; }

    public void setUser_achieved_id(int user_achieved_id) { this.user_achieved_id = user_achieved_id; }

    public int getAchievement_id() { return achievement_id; }

    public void setAchievement_id(int achievement_id) { this.achievement_id = achievement_id; }

    public int getUser_id() { return user_id; }

    public void setUser_id(int user_id) { this.user_id = user_id; }

    public String getDatetime_achieved() { return datetime_achieved; }

    public void setDatetime_achieved(String datetime_achieved) { this.datetime_achieved = datetime_achieved; }

    @Override
    public String toString() {
        return "UserAchievement{" +
                "user_achieved_id=" + user_achieved_id +
                ", achievement_id='" + achievement_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", datetime_achieved=" + datetime_achieved +
                '}';
    }

}
