package com.shaunhossain.networklearn2;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @POST("posts")
    Call<Posts> CreatePost(@Body Posts posts);

    @FormUrlEncoded
    @POST("posts")
    Call<Posts>CreatePost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String body
    );

    @FormUrlEncoded
    @POST("posts")
    Call<Posts>CreatePost(@FieldMap Map<String , String> posts);

    @PUT("posts/{id}")
    Call<Posts>UpdatePost(@Path("id")int id,@Body Posts posts);

    @PATCH("posts/{id}")
    Call<Posts>PatchPost(@Path("id")int id,@Body Posts posts);


    /*
    * PUT and Patch request will not support Field or FieldMap to carry the body of the json . we must have to use Body(@Body)
    * anotation to carry the body*/
    @PUT("posts/{id}")
    Call<Posts>PatchUpdatPost(@Path("id")int id,@FieldMap Map<String, String>parameters);
}
