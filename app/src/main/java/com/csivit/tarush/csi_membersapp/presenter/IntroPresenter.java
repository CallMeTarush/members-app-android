package com.csivit.tarush.csi_membersapp.presenter;


import android.content.Context;

import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.service.MembersService;

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
        void onAuthFailure(Error e);
    }

    public void doRegister(User user){
        membersService.getAPI().doRegister(user.getUserEmail(),user.getUserPassword(),
                user.getUserRegNo(),user.getUserPhoneNo(),user.getUserDisplayName(),
                user.getUserMemberType(),user.getUserKey()).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse result = response.body();

                if(result != null){
                    mListener.onAuthSuccess(result);
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                try {
                    throw new InterruptedException("Error in communicating with API");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
