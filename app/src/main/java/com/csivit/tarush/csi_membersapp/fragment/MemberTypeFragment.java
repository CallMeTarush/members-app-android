package com.csivit.tarush.csi_membersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csivit.tarush.csi_membersapp.R;


public class MemberTypeFragment extends Fragment {

    //TODO: Connect it to the Activity
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_member_type, container, false);

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.mem_type_group);
        final EditText memkeyinput = ((EditText) v.findViewById(R.id.input_mem_key));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton1 || checkedId == R.id.radioButton2){
                    memkeyinput.setVisibility(View.VISIBLE);
                }else
                    memkeyinput.setVisibility(View.GONE);
            }
        });

        return v;
    }
}
