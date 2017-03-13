package com.csivit.tarush.csi_membersapp.service;


import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;
import com.csivit.tarush.csi_membersapp.model.response.EventsResponse;
import com.csivit.tarush.csi_membersapp.model.system.Event;
import com.csivit.tarush.csi_membersapp.model.system.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MembersAPI {

    @FormUrlEncoded
    @POST("auth/login")
    Call<AuthResponse> doLogin(@Field("username") String username, @Field("password") String password);


    @POST("auth/register")
    Call<AuthResponse> doRegister(@Body User a);

    @FormUrlEncoded
    @POST("users/password")
    Call<AuthResponse> changePassword(@Field("old_password") String oldPassword,
                                      @Field("new_password") String newPassword);
    @GET("events")
    Call<EventsResponse> getEvents();

    @GET("events/{id}")
    Call<Event> getEvent(@Path("id") String id);

    @GET("user")
    Call<User> getUser();


    //TODO: Add other routes
}
