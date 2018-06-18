package com.example.tanphirum.firstapplication.webservice;

import com.example.tanphirum.firstapplication.pojo.UserItem;
import com.example.tanphirum.firstapplication.pojo.register.RegisterItem;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIUserInterface {

    @FormUrlEncoded
    @POST("/api/register")
    Call<RegisterItem> doRegister(@Field("email") String email, @Field("password") String password, @Field("delay") String ss);

    @POST("/api/login?")
    Call<Object> doLogin(@Body RegisterItem user, @Query(value = "delay",encoded = true) int delay);

    @GET("/api/users?")
    Call<UserItem> doGetUserList(@Query("page") String page);
}
