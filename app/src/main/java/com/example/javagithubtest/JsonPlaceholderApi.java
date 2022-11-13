package com.example.javagithubtest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceholderApi {
    @GET("search/users?q=Q")
    Call<List<SearchUsers>> getUsers();

    @GET("search/topics?q=Q")
    Call<List<SearchTopics>> getTopics();


}
