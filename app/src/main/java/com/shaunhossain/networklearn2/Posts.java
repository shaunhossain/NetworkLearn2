package com.shaunhossain.networklearn2;

public class Posts {

    private int userId;
    private int id;
    private String title;
    private String body;

    public Posts(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }


    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }




}
