package com.csivit.tarush.csi_membersapp.Fragments;



import com.csivit.tarush.csi_membersapp.CustomAdapters.CustomList;
import com.csivit.tarush.csi_membersapp.activity.MainActivity;
import com.csivit.tarush.csi_membersapp.R;

import com.csivit.tarush.csi_membersapp.model.response.EventDetailsResponse;
import com.csivit.tarush.csi_membersapp.model.response.EventsResponse;
import com.csivit.tarush.csi_membersapp.model.system.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Events_fragment extends ListFragment {

    ListView list;
    String[] events = {
            "Codespace",
            "Riddler",
            "Yuvana"
    } ;
    Integer[] imageId = {
            R.drawable.icon1,
            R.drawable.icon2,
            R.drawable.icon3
    };

    ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
    SimpleAdapter adapter;

    public Events_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity())
                .setActionBarTitle("Events");

        View view = inflater.inflate(R.layout.fragment_events_fragment, container, false);

        HashMap<String, String> map = new HashMap<String, String>();

        for(int i=0;i<events.length;i++)
        {
            map = new HashMap<String, String>();
            map.put("Event",events[i]);
            map.put("Image",Integer.toString(imageId[i]));

            data.add(map);
        }
        //Keys in Map
        String[] from = {"Event","Image"};

        //Ids of Views
        int[] to = {R.id.event_Title,R.id.img_thumb};

        //Adapter
        adapter = new SimpleAdapter(getActivity(),data,R.layout.event_list_item,from,to);
        setListAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Toast.makeText(getActivity(), data.get(pos).get("Event"),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

