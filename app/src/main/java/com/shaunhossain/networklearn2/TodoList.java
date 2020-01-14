package com.shaunhossain.networklearn2;

import com.google.gson.annotations.SerializedName;

public class TodoList {

    private int userId;
    private int id;
    private String title;
    @SerializedName("completed")
    boolean isCompleted;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }



}
