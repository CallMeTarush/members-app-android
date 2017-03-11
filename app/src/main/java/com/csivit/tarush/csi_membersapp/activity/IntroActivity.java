package com.csivit.tarush.csi_membersapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.fragment.MemberTypeFragment;
import com.csivit.tarush.csi_membersapp.fragment.RegistrationFragment;
import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;
import com.csivit.tarush.csi_membersapp.presenter.IntroPresenter;
import com.csivit.tarush.csi_membersapp.service.DataStore;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2Fragment;


public class IntroActivity extends AppIntro{

    private IntroPresenter introPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("Computer Society of India", "Hello", R.drawable.csi_logo02,getResources().getColor(R.color.colorPrimary)));
        addSlide(RegistrationFragment.newInstance(null, null));
        addSlide(new MemberTypeFragment());

        showSkipButton(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);

        //TODO: Get Values from the Member Type Fragment and update the User Object

        doSignUp();
        // Do something when users tap on Done button.
    }

    private void doSignUp(){
        final ProgressDialog progressDialog = new ProgressDialog(IntroActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        //TODO: Improve this to segregate logic and presentation
        introPresenter = new IntroPresenter(this, new IntroPresenter.IntroPresenterListener() {
            @Override
            public void onAuthSuccess(AuthResponse authResponse) {
                progressDialog.hide();
                if(authResponse.getStatus().getStatusCode() == 201){
                    SharedPreferences getPrefs = PreferenceManager
                            .getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putString("userToken", authResponse.getUserToken());
                    e.apply();

                    Intent i = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(i);

                    finish();
                }
            }

            @Override
            public void onAuthFailure(Error e) {

            }
        });

        introPresenter.doRegister(DataStore.getInstance().getRegisteringUser());
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);


        if(oldFragment instanceof RegistrationFragment){
            RegistrationFragment f = (RegistrationFragment) oldFragment;
            f.saveDetails();
        }
    }

}
