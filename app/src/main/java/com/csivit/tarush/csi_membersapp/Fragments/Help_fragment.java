package com.csivit.tarush.csi_membersapp.Fragments;



import com.csivit.tarush.csi_membersapp.activity.MainActivity;
import com.csivit.tarush.csi_membersapp.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Help_fragment extends Fragment {


    public Help_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle("Help");
        return inflater.inflate(R.layout.help_fragment, container, false);
    }

}
