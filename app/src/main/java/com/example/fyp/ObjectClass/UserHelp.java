package com.example.fyp.ObjectClass;

public class UserHelp {

    private int userhelp_id, service_id, org_id, user_id, goal_id, helped;

    public UserHelp(int service_id, int user_id, int goal_id) {
        this.service_id = service_id;
        this.user_id = user_id;
        this.goal_id = goal_id;
    }

    public int getUserhelp_id() {
        return userhelp_id;
    }

    public void setUserhelp_id(int userhelp_id) {
        this.userhelp_id = userhelp_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public int getOrg_id() {
        return org_id;
    }

    public void setOrg_id(int org_id) {
        this.org_id = org_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGoal_id() {
        return goal_id;
    }

    public void setGoal_id(int goal_id) {
        this.goal_id = goal_id;
    }

    public int getHelped() {
        return helped;
    }

    public void setHelped(int helped) {
        this.helped = helped;
    }

    @Override
    public String toString() {
        return "UserHelp{" +
                "userhelp_id=" + userhelp_id +
                ", service_id=" + service_id +
                ", org_id=" + org_id +
                ", user_id=" + user_id +
                ", goal_id=" + goal_id +
                ", helped=" + helped +
                '}';
    }
}
