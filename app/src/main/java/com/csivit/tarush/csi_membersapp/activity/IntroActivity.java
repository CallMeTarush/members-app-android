package com.csivit.tarush.csi_membersapp.activity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.fragments.MemberTypeFragment;
import com.csivit.tarush.csi_membersapp.fragments.RegistrationFragment;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;


public class IntroActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("Some Title", "Gello", R.drawable.csi_logo02,getResources().getColor(R.color.colorPrimary)));
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


        // Do something when users tap on Done button.
    }

    private void onSignUp(){
        final ProgressDialog progressDialog = new ProgressDialog(IntroActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();
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
