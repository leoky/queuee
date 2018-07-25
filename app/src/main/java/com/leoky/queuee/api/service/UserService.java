package com.leoky.queuee.api.service;

import com.leoky.queuee.api.model.Home;
import com.leoky.queuee.api.model.RepoQueue;
import com.leoky.queuee.api.model.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
    Call<Home> updateCStatus(@Path("id") String id,
                             @Field("newStatus") String cstatus);

    @FormUrlEncoded
    @POST("doctor/done/{queue_id}")
    Call<RepoQueue> queueDone(@Path("queue_id") String id,
                                 @Field("result") String result);

//    @FormUrlEncoded
//    @POST("doctor/done/{queue_id}")
//    Call<RepoQueue> queueCancel(@Path("queue_id") String id,
//                              @Field("result") String result);


    @GET("queue/list/d/{doctor_id}")
    Call<RepoQueue> getQueueList(@Path("doctor_id")String id);

}
