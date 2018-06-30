package com.leoky.queuee.api.service;

import com.leoky.queuee.api.model.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("users/login")
    Call<UserData> loginRequest(@Field("email") String email,
                                @Field("password") String password);
}
