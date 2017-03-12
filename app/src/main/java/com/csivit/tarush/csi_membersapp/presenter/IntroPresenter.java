package com.csivit.tarush.csi_membersapp.presenter;


import android.content.Context;
import android.util.Log;

import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.service.MembersService;

import java.io.IOException;

import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroPresenter {

    private final Context context;
    private final IntroPresenterListener mListener;
    private final MembersService membersService;

    public IntroPresenter(Context context, IntroPresenterListener mListener) {
        this.context = context;
        this.mListener = mListener;
        this.membersService = new MembersService();
    }

    public interface IntroPresenterListener{
        void onAuthSuccess(AuthResponse authResponse);
        void onAuthFailure();
    }

    public void doRegister(User user){
        membersService.getAPI().doRegister(user).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {

                try {
                    final RequestBody copy = call.request().body();
                    final Buffer buffer = new Buffer();
                    if (copy != null)
                        copy.writeTo(buffer);
                    else
                        Log.i(" "," ");
                    Log.i("IP",buffer.readUtf8());
                } catch (final IOException e) {

                }


                AuthResponse result = response.body();
                if(result != null){
                    mListener.onAuthSuccess(result);
                }
                else mListener.onAuthFailure();
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                try {
                    throw new InterruptedException("Error in communicating with API");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mListener.onAuthFailure();
            }
        });
    }


}
