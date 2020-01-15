package com.shaunhossain.networklearn2;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface NetworkService {

    @GET("todos")
    Call<List<TodoList>> getList(
            @Query("userId")int userId,
            @Query("_sort") String id,
            @Query("_order") String order
    );

    @GET("todos")
    Call<List<TodoList>> getList(@QueryMap Map<String, String>parameters);

    @GET
    Call<List<TodoList>> getList(@Url String Url);
}
