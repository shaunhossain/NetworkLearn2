package com.shaunhossain.networklearn2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NetworkService {

    @GET("todos/")
    Call<List<TodoList>> getList();
}
