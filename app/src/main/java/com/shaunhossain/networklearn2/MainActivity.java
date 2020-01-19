package com.shaunhossain.networklearn2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView contentText;
    NetworkService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentText = findViewById(R.id.content);

        //use to see the http interceptor which request is sended and what is receiving

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpclient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
        //client is added for http interceptor
                .client(okHttpclient)
                .build();
        service = retrofit.create(NetworkService.class);
        getResultOfTodList();

        //CreatePost();
        UpdatePosts();


        /*
         * PUT and Patch request will not support Field or FieldMap to carry the body of the json . we must have to use Body(@Body)
         * anotation to carry the body*/

        //PlayWithPatchUpdatePost();

        //DetetePost();


    }
    private void DetetePost(){

        Call<Void> call = service.DeletePost(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                contentText.setText("Code :"+response.code());

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                contentText.setText(t.getMessage());

            }
        });
    }

    private void PlayWithPatchUpdatePost(){

        /*
         * PUT and Patch request will not support Field or FieldMap to carry the body of the json . we must have to use Body(@Body)
         * anotation to carry the body*/
        //Warning : not support FieldMap or Field by PUT or Patch request.

        Map<String,String>updatePost= new HashMap<>();
        updatePost.put("userId","11");
        updatePost.put("title","I am going to learn retrofit");
        updatePost.put("body","i wll also learn all the necessary components to finish my android learning goal");


        Call<Posts>call=service.PatchUpdatPost(1,updatePost);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                if (!response.isSuccessful()){

                    contentText.setText("code:"+response.code());
                }

                Posts postResponse = response.body();

                String content="";
                content +="Response code: "+response.code()+"\n";
                content += "User Id :"+postResponse.getUserId()+"\n";
                content += "Id :"+postResponse.getId()+"\n";
                content += "Title :"+postResponse.getTitle()+"\n";
                content +="Body :"+postResponse.getBody()+"\n\n\n";

                contentText.append(content);


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                contentText.setText(t.getMessage());


            }
        });

    }

    private void UpdatePosts(){

        //Posts posts = new Posts(21,"i am a android developer.","i have to learn Kotlin");
        Posts posts = new Posts(21,null,"i have to learn Kotlin");

        //testing dynemic header using HeaderMap
        Map<String,String>header=new HashMap<>();
        header.put("dynemic1-Header1","123");
        header.put("dynemic2-Header2","abc");

        //Call<Posts>call =service.CreatePost(posts);
        Call<Posts>call =service.UpdatePost(header,2,posts);

        //Call<Posts>call=service.PatchPost(1, posts);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                if (!response.isSuccessful()){

                    contentText.setText("code:"+response.code());
                }

                Posts postResponse = response.body();

                String content="";
                content +="Response code: "+response.code()+"\n";
                content += "User Id :"+postResponse.getUserId()+"\n";
                content += "Id :"+postResponse.getId()+"\n";
                content += "Title :"+postResponse.getTitle()+"\n";
                content +="Body :"+postResponse.getBody()+"\n\n\n";

                contentText.append(content);


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                contentText.setText(t.getMessage());


            }
        });


    }

    private void CreatePost(){

       // Posts posts = new Posts(21,"i am a android developer.","i have to learn Kotlin");

        //Call<Posts>call=service.CreatePost(21,"i am a android developer.","i have to learn Kotlin and dejango");

        Map<String, String>post = new HashMap<>();
        post.put("userId","11");
        post.put("title","i love android development");
        post.put("body","i will lead my life to it");

        Call<Posts>call =service.CreatePost(post);

        call.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {

                if (!response.isSuccessful()){

                    contentText.setText("code:"+response.code());
                }

                Posts postResponse = response.body();

                    String content="";
                    content +="Response code: "+response.code()+"\n";
                    content += "User Id :"+postResponse.getUserId()+"\n";
                    content += "Id :"+postResponse.getId()+"\n";
                    content += "Title :"+postResponse.getTitle()+"\n";
                    content +="Completed :"+postResponse.getBody()+"\n\n\n";

                    contentText.append(content);


            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

                contentText.setText(t.getMessage());


            }
        });


    }

    private void getResultOfTodList(){

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "2");
        parameters.put("_sort", "id");
        parameters.put("_order", "desec");

        //Call<List<TodoList>> call = service.getList(1 , "id" ,"asec");

        Call<List<TodoList>> call = service.getList(parameters);
        //Call<List<TodoList>> call = service.getList("todos/1");

        call.enqueue(new Callback<List<TodoList>>() {
            @Override
            public void onResponse(Call<List<TodoList>> call, Response<List<TodoList>> response) {

                //contentText.setText(response.toString());

                if (!response.isSuccessful()){

                    contentText.setText("code:"+response.code());
                }

                List<TodoList>todolist=response.body();
                for(TodoList todoList: todolist){

                    String content="";
                    content += "User Id :"+todoList.getUserId()+"\n";
                    content += "Id :"+todoList.getId()+"\n";
                    content += "Title :"+todoList.getTitle()+"\n";
                    content +="Completed :"+todoList.isCompleted()+"\n\n\n";

                    contentText.append(content);


                }

            }

            @Override
            public void onFailure(Call<List<TodoList>> call, Throwable t) {

                //Toast.makeText(MainActivity.this,"code is not working"+t,Toast.LENGTH_SHORT).show();
                contentText.setText(t.getMessage());

            }
        });
    }
}
