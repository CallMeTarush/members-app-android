package com.csivit.tarush.csi_membersapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.service.DataStore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.input_password) EditText etPassword;

    @BindView(R.id.input_name) EditText etDisplayName;

    @BindView(R.id.input_regno) EditText etRegNo;

    @BindView(R.id.input_email) EditText etEmail;

    @BindView(R.id.input_phone) EditText etPhone;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    public static RegistrationFragment newInstance(String param1, String param2) {
        RegistrationFragment fragment = new RegistrationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        return v;
    }



}
