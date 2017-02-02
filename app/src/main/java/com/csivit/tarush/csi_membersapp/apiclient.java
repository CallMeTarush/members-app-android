package com.csivit.tarush.csi_membersapp;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tarush on 2/2/2017.
 */

public class apiclient {

        public static final String BASE_URL = "https://private-anon-feef5138b9-csimem.apiary-mock.com/";
        private static Retrofit retrofit = null;


        public static Retrofit getClient() {
            if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

}
