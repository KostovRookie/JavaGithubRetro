package com.example.javagithubtest;
import com.google.gson.annotations.SerializedName;


public class SearchUsers {

    private String userId;
    private int id;
    private String title;

    @SerializedName ("body")
    private String text;


    public String getlogin() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
