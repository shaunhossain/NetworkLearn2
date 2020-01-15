package com.shaunhossain.networklearn2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentText = findViewById(R.id.content);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NetworkService service = retrofit.create(NetworkService.class);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "2");
        parameters.put("_sort", "id");
        parameters.put("_order", "desec");

        //Call<List<TodoList>> call = service.getList(1 , "id" ,"asec");

        Call<List<TodoList>> call = service.getList(parameters);

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
