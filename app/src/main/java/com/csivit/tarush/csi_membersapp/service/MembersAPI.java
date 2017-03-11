package com.csivit.tarush.csi_membersapp.service;


import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;
import com.csivit.tarush.csi_membersapp.model.response.EventsResponse;
import com.csivit.tarush.csi_membersapp.model.system.Event;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MembersAPI {

    @FormUrlEncoded
    @POST("auth/login")
    Call<AuthResponse> doLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register")
    Call<AuthResponse> doRegister(@Field("email") String email, @Field("password") String password,
                                  @Field("username") String regNo, @Field("phone") String phone,
                                  @Field("name") String name, @Field("mem_type") String memType,
                                  @Field("key") String regKey);

    @FormUrlEncoded
    @POST("users/password")
    Call<AuthResponse> changePassword(@Field("old_password") String oldPassword,
                                      @Field("new_password") String newPassword);
    @GET("events")
    Call<EventsResponse> getEvents();


    //TODO: Add other routes
}
