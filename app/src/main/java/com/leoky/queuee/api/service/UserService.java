package com.leoky.queuee.api.service;

import com.leoky.queuee.api.model.DoctorData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("doctor/login")
    Call<DoctorData> loginRequest(@Field("email") String email,
                                  @Field("password") String password);

//    @GET("doctor/list/john@gmail.com")
//    Call<DoctorData> takedata();
}
