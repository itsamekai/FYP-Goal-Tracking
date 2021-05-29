package com.example.fyp.ObjectClass;

public class UsersGoal {
    private int goal_id, goal_type_id, user_id, accomplished;
    private String goal_name, goal_desc, datetime_created, datetime_completed;

    public UsersGoal(int goal_type_id, int user_id, String goal_name, String goal_desc) {
        this.goal_type_id = goal_type_id;
        this.user_id = user_id;
        this.goal_name = goal_name;
        this.goal_desc = goal_desc;
    }

    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public int getGoal_type_id() {
        return goal_type_id;
    }

    public void setGoal_type_id(int goal_type_id) {
        this.goal_type_id = goal_type_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAccomplished() {
        return accomplished;
    }

    public void setAccomplished(int accomplished) {
        this.accomplished = accomplished;
    }

    public String getGoal_name() {
        return goal_name;
    }

    public void setGoal_name(String goal_name) {
        this.goal_name = goal_name;
    }

    public String getGoal_desc() {
        return goal_desc;
    }

    public void setGoal_desc(String goal_desc) {
        this.goal_desc = goal_desc;
    }

    public String getDatetime_created() {
        return datetime_created;
    }

    public void setDatetime_created(String datetime_created) {
        this.datetime_created = datetime_created;
    }

    public String getDatetime_completed() {
        return datetime_completed;
    }

    public void setDatetime_completed(String datetime_completed) {
        this.datetime_completed = datetime_completed;
    }

    @Override
    public String toString() {
        return "UsersGoal{" +
                "goal_id=" + goal_id +
                ", goal_type_id=" + goal_type_id +
                ", user_id=" + user_id +
                ", accomplished=" + accomplished +
                ", goal_name='" + goal_name + '\'' +
                ", goal_desc='" + goal_desc + '\'' +
                ", datetime_created='" + datetime_created + '\'' +
                ", datetime_completed='" + datetime_completed + '\'' +
                '}';
    }
}
