package com.leoky.queuee.api.service;

import com.leoky.queuee.api.model.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @FormUrlEncoded
    @POST("doctor/login")
    Call<UserData> loginRequest(@Field("email") String email,
                                @Field("password") String password);

    @FormUrlEncoded
    @POST("doctor/update/email/{id}")
    Call<UserData> updateEmail(@Path("id") String id,
                                @Field("newEmail") String email);

//    @GET("doctor/list/john@gmail.com")
//    Call<UserData> takedata();
}
