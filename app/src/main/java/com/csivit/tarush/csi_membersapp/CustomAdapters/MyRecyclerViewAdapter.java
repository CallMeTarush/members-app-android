package com.csivit.tarush.csi_membersapp.CustomAdapters;

/**
 * Created by palashgo on 11/3/17.
 */

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.csivit.tarush.csi_membersapp.Fragments.Event_desc_fragment;
import com.csivit.tarush.csi_membersapp.model.system.Event;
import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.service.DataStore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private ArrayList<Event> mDataset;
    private DateFormat fmt = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
    static PassEvent passEvent;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView id,label,time,venue,date;

        public DataObjectHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.event_id);
            label = (TextView) itemView.findViewById(R.id.event_Title);
            time = (TextView) itemView.findViewById(R.id.event_time);
            venue = (TextView) itemView.findViewById(R.id.event_venue);
            date = (TextView) itemView.findViewById(R.id.event_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String event_id = ((TextView) v.findViewById(R.id.event_id)).getText().toString();
            Log.i("RA",event_id);
            passEvent.eventPasser(event_id);
            Log.i("RA",event_id);

        }
    }



    public MyRecyclerViewAdapter(ArrayList<Event> myDataset,PassEvent mypassevent) {
        mDataset = myDataset;
        passEvent = mypassevent;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.id.setText(mDataset.get(position).getEventId()+"");
        holder.label.setText(mDataset.get(position).getEventName());
        holder.time.setText(mDataset.get(position).getEventTime());
        holder.venue.setText(mDataset.get(position).getEventVenue());
        holder.date.setText(fmt.format(mDataset.get(position).getEventDate()));

    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface PassEvent {
        void eventPasser(String id);
    }




}