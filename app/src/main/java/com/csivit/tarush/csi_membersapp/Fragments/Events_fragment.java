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
import com.csivit.tarush.csi_membersapp.CustomAdapters.MyRecyclerViewAdapter;

public class Events_fragment extends Fragment {

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
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String,String>>();
    SimpleAdapter adapter;

    public Events_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getEvents());
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onStart() {

        super.onStart();

       /* getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Toast.makeText(getActivity(), data.get(pos).get("Event"),Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    private ArrayList<Event> getEvents() {
        ArrayList results = new ArrayList<Event>();
        for (int index = 0; index < 20; index++) {
            Event obj = new Event();//Add event code here
            obj.setEventName("Riddler");
            results.add(index, obj);
        }
        return results;
    }
}

