    package com.csivit.tarush.csi_membersapp.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.service.DataStore;
import com.csivit.tarush.csi_membersapp.service.MembersAPI;
import com.csivit.tarush.csi_membersapp.service.MembersService;
import com.csivit.tarush.csi_membersapp.model.response.AuthResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mRegNoView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setActivityBackgroundColor(0x5D95FC);

        // Set up the login form.

        mRegNoView = (AutoCompleteTextView) findViewById(R.id.login_username);

        mPasswordView = (EditText) findViewById(R.id.login_password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }





    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mRegNoView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String reg_no = mRegNoView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid registeration address.
        if (TextUtils.isEmpty(reg_no)) {
            mRegNoView.setError(getString(R.string.error_field_required));
            focusView = mRegNoView;
            cancel = true;
        } else if (!isRegValid(reg_no)) {
            mRegNoView.setError(getString(R.string.error_invalid_reg));
            focusView = mRegNoView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuthTask = new UserLoginTask(reg_no, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isRegValid(String reg_no) {
        //TODO: Replace this with new logic
        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with new logic
        return password.length() > 4;
    }





    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mRegNo;
        private final String mPassword;
        private MembersAPI membersAPI;
        private AuthResponse authResponse;

        UserLoginTask(String regno, String password) {
            mRegNo = regno;
            mPassword = password;
            membersAPI = new MembersService().getAPI();
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            Call<AuthResponse> call = membersAPI.doLogin(mRegNo,mPassword);

            call.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    if(response.code()==200) {
                        authResponse = response.body();


                        SharedPreferences getPrefs = PreferenceManager
                                .getDefaultSharedPreferences(getBaseContext());
                        SharedPreferences.Editor e = getPrefs.edit();
                        e.putBoolean("loginReq", false);
                        e.putString("token",authResponse.getUserToken());
                        e.apply();

                        DataStore.getInstance().setJwtToken(authResponse.getUserToken());


                        finish();

                        Intent backtomain = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(backtomain);
                        LoginActivity.this.finish();

                    } else {
                            mPasswordView.setError(getString(R.string.error_incorrect_password));
                            mPasswordView.requestFocus();
                        }

                }


                @Override
                public void onFailure(Call call, Throwable t) {
                    call.cancel();
                }
            });


            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;


        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }
}

