package com.csivit.tarush.csi_membersapp.Fragments;



import com.csivit.tarush.csi_membersapp.R;

import com.csivit.tarush.csi_membersapp.model.response.EventsResponse;
import com.csivit.tarush.csi_membersapp.model.system.Event;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import com.csivit.tarush.csi_membersapp.CustomAdapters.MyRecyclerViewAdapter;
import com.csivit.tarush.csi_membersapp.service.DataStore;
import com.csivit.tarush.csi_membersapp.service.MembersAPI;
import com.csivit.tarush.csi_membersapp.service.MembersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events_fragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public Events_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_events_fragment, container, false);
        fetchEvents(view);
        return view;
    }

    private void fetchEvents(View view){
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        MembersAPI membersAPI = new MembersService().getAPI();
        Call<EventsResponse> call = membersAPI.getEvents();
        final ArrayList results = new ArrayList<Event>();
        final MyRecyclerViewAdapter.PassEvent mypassevent=(MyRecyclerViewAdapter.PassEvent) getActivity();

        call.enqueue(new Callback<EventsResponse>() {
            @Override
            public void onResponse(Call<EventsResponse> call, Response<EventsResponse> response) {
                Log.i("AL",response.code()+"");
                if(response.code() == 200) {
                    List<Event> eventList = response.body().getEvents();
                    for (int i = 0; i < eventList.size(); i++) {
                        results.add(i, eventList.get(i));
                    }
                    mAdapter = new MyRecyclerViewAdapter(results,mypassevent);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<EventsResponse> c,Throwable t){
                }
            });


    }
    @Override
    public void onStart() {

        super.onStart();

    }


}

