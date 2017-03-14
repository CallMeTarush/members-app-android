package com.csivit.tarush.csi_membersapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.fragment.MemberTypeFragment;
import com.csivit.tarush.csi_membersapp.fragment.RegistrationFragment;
import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.presenter.IntroPresenter;
import com.csivit.tarush.csi_membersapp.service.DataStore;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2Fragment;


public class IntroActivity extends AppIntro{

    private IntroPresenter introPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("", "Hello", R.drawable.csi,getResources().getColor(R.color.colorPrimary)));
        addSlide(new RegistrationFragment());
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


        int checked=((RadioGroup)findViewById(R.id.mem_type_group)).getCheckedRadioButtonId();
        if(checked == R.id.radioButton1) checked=3;
        else if(checked ==R.id.radioButton2) checked=2;
        else if(checked ==R.id.radioButton3) checked=0;

        User r= DataStore.getInstance().getRegisteringUser();
        r.setUserMemberType(checked);
        r.setUserKey(((EditText) findViewById(R.id.input_mem_key)).getText().toString());
        DataStore.getInstance().setRegisteringUser(r);


        doSignUp();

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
                progressDialog.dismiss();
                if(authResponse.getStatus().getStatusCode() == 201){

                    SharedPreferences getPrefs = PreferenceManager
                            .getDefaultSharedPreferences(getBaseContext());
                    SharedPreferences.Editor e = getPrefs.edit();
                    e.putString("token", authResponse.getUserToken());
                    e.putBoolean("loginReq",false);
                    e.apply();


                    Intent i = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(i);

                    finish();
                }
            }

            @Override
            public void onAuthFailure() {

                progressDialog.dismiss();
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
