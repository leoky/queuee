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

    @FormUrlEncoded
    @POST("doctor/update/password/{id}")
    Call<UserData> updatePassword(@Path("id") String id,
                                    @Field("currentPassword") String pass,
                                    @Field("newPassword") String newPass);

    @FormUrlEncoded
    @POST("doctor/update/phone/{id}")
    Call<UserData> updatePhone(@Path("id") String id,
                                  @Field("newPhone") String phone);

    @FormUrlEncoded
    @POST("doctor/update/estimate/{id}")
    Call<UserData> updateCSTime(@Path("id") String id,
                                  @Field("newEstimate") String time);

    @FormUrlEncoded
    @POST("doctor/update/status/{id}")
    Call<UserData> updateCStatus(@Path("id") String id,
                                 @Field("newStatus") String cstatus);

}
