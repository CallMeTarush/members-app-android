package com.csivit.tarush.csi_membersapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.activity.IntroActivity;
import com.csivit.tarush.csi_membersapp.activity.LoginActivity;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.service.DataStore;

import butterknife.BindView;
import butterknife.ButterKnife;



public class RegistrationFragment extends Fragment {

    @BindView(R.id.input_password) EditText etPassword;
    @BindView(R.id.input_name) EditText etDisplayName;
    @BindView(R.id.input_regno) EditText etRegNo;
    @BindView(R.id.input_email) EditText etEmail;
    @BindView(R.id.input_phone) EditText etPhone;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    private TextView mLoginBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void saveDetails(){
        User newUser = new User();
        newUser.setUserDisplayName(etDisplayName.getText().toString());
        newUser.setUserEmail(etEmail.getText().toString());
        newUser.setUserRegNo(etRegNo.getText().toString());
        newUser.setUserPassword(etPassword.getText().toString());
        newUser.setUserPhoneNo(etPhone.getText().toString());

        DataStore.getInstance().setRegisteringUser(newUser);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_registration, container, false);

        ButterKnife.bind(this, v);
        mLoginBtn = (TextView) v.findViewById(R.id.button);
        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }



}
