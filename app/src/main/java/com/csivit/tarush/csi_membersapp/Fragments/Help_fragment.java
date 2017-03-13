package com.csivit.tarush.csi_membersapp.Fragments;



import com.csivit.tarush.csi_membersapp.activity.MainActivity;
import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.service.DataStore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Help_fragment extends Fragment {


    public Help_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.help_fragment, container, false);

        ImageView fb = (ImageView) v.findViewById(R.id.fb_icon);
        ImageView mail = (ImageView) v.findViewById(R.id.mail_icon);
        TextView self_name = (TextView) v.findViewById(R.id.self_name);
        TextView self_email = (TextView) v.findViewById(R.id.self_email);
        TextView self_reg = (TextView) v.findViewById(R.id.self_reg);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/csivit";
                Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:askcsivit@gmail.com");
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(emailIntent);
            }
        });

        User loggedInUser = DataStore.getInstance().getRegisteringUser();

        self_email.setText("Email : "+loggedInUser.getUserEmail());
        self_name.setText("Name : "+loggedInUser.getUserDisplayName());
        self_reg.setText("Reg No: "+loggedInUser.getUserRegNo());

        return v;
    }

}
