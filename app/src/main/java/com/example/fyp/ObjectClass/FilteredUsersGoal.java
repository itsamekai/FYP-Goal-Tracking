package com.example.fyp.ObjectClass;

public class FilteredUsersGoal {

    // this class is used for searching the user's goals


    private String name, category, goalName, accomplished;

    public FilteredUsersGoal(String name, String category, String goalName, String accomplished) {
        this.name = name;
        this.category = category;
        this.goalName = goalName;
        this.accomplished = accomplished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getAccomplished() {
        return accomplished;
    }

    public void setAccomplished(String accomplished) {
        this.accomplished = accomplished;
    }

    @Override
    public String toString() {
        return "FilteredUsersGoal{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", goalName='" + goalName + '\'' +
                ", accomplished='" + accomplished + '\'' +
                '}';
    }
}
