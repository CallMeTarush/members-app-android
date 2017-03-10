package com.csivit.tarush.csi_membersapp.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MembersService {

    private static final String BASE_URL = "http://api-memberapp.csivit.com:8080/api/";
    private static Retrofit retrofit = null;
    private String token;

    public MembersService(){
        token = DataStore.getInstance().getJwtToken();
    }

    public MembersService(String token){
        this.token = token;
    }

    private OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            if (originalRequest.url().toString().contains("auth/")) {
                return chain.proceed(originalRequest);
            }

            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "JWT " + token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

    public MembersAPI getAPI() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                        .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(MembersAPI.class);
    }



}
