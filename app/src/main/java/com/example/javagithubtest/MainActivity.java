package com.example.javagithubtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView textViewResult;

    private JsonPlaceholderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);
        //"https://api.github.com/search/
        //https://api.github.com/search/users?q=Q


        //"https://jsonplaceholder.typicode.com/" // за тестване, но тук си имаме масив и е точно

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceholderApi.class);

        getPosts();
        // getTopics();
    }

    private void getPosts() {

        Call<List<SearchUsers>> call = jsonPlaceHolderApi.getUsers();

        call.enqueue(new Callback<List<SearchUsers>>() {      // new thread independent from main thread// опитах с execute , стана not responsive??
            @Override
            public void onResponse(Call<List<SearchUsers>> call, Response<List<SearchUsers>> response) {

                if (!response.isSuccessful()) { /// if i get bad response, not 404, 401 or similar, тоест дали дърпам нещо грешно
                    textViewResult.setText("Code: " + response.code()); // да ми покаже грешката , сега проблема е
                    return;                                             // че дърпам object, а не масив от json
                }
                List<SearchUsers> users = response.body();   // това викам при успех при новата нишка

                for (SearchUsers user : users) {
                    String content = "";
                    // content = content + "ID: " + user.getId() + "\n";
                    content = content + "UserID: " + user.getlogin() + "\n";
                    // content = content + "Title: " + user.getTitle() + "\n";
                    //  content = content + "Text: " + user.getText() + "\n\n";

                    textViewResult.append(content); // всичко прикачвам към textView, който е към layout

                }
            }

            @Override
            public void onFailure(Call<List<SearchUsers>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getTopics() {   // disabled for testing purposes, same functions
        Call<List<SearchTopics>> call = jsonPlaceHolderApi.getTopics();
        call.enqueue(new Callback<List<SearchTopics>>() {
            @Override

            public void onResponse(Call<List<SearchTopics>> call, Response<List<SearchTopics>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<SearchTopics> topics = response.body();
                for (SearchTopics topic : topics) {
                    String content = "";
                    content = content + "ID: " + topic.getId() + "\n";
                    content = content + "Post ID: " + topic.getPostId() + "\n";
                    content = content + "Name: " + topic.getName() + "\n";
                    content = content + "Email: " + topic.getEmail() + "\n";
                    content = content + "Text: " + topic.getText() + "\n\n";

                    textViewResult.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<SearchTopics>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });

    }

}