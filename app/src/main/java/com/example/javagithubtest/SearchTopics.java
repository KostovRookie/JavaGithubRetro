package com.example.javagithubtest;
import com.google.gson.annotations.SerializedName;

public class SearchTopics {
    private int postId;
    private int id;
    private String name;
    private String email;

    @SerializedName("body")
    private String text;

    public SearchTopics() {
    }

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
