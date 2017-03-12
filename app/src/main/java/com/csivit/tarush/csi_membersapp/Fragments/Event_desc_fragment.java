package com.csivit.tarush.csi_membersapp.Fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.csivit.tarush.csi_membersapp.CustomAdapters.MyRecyclerViewAdapter;
import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.activity.IntroActivity;
import com.csivit.tarush.csi_membersapp.model.response.EventsResponse;
import com.csivit.tarush.csi_membersapp.model.system.Event;
import com.csivit.tarush.csi_membersapp.service.DataStore;
import com.csivit.tarush.csi_membersapp.service.MembersAPI;
import com.csivit.tarush.csi_membersapp.service.MembersService;

import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event_desc_fragment extends Fragment {
    View root;
    private DateFormat fmt = new SimpleDateFormat("dd MMMM, yyyy", Locale.US);
    private ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        root = (View) inflater.inflate(R.layout.event_desc,container,false);
        progressDialog = new ProgressDialog(inflater.getContext(),R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        return root;
    }
    public void loadEvent(String id){
        Log.i("Fragment",id);

        MembersAPI membersAPI = new MembersService().getAPI();
        Call<Event> call = membersAPI.getEvent(id);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                Log.i("AL",response.code()+"");
                if(response.code() == 200) {
                    Event event = response.body();
                    ((TextView) getActivity().findViewById(R.id.event_desc_title)).setText(event.getEventName());
                    ((TextView) getActivity().findViewById(R.id.descTimeField)).setText(event.getEventTime());
                    ((TextView) getActivity().findViewById(R.id.descVenueField)).setText(event.getEventVenue());
                    ((TextView) getActivity().findViewById(R.id.event_desc_body)).setText(event.getEventDescription());
                    ((TextView) getActivity().findViewById(R.id.descDateField)).setText(fmt.format(event.getEventDate()));

                    final ImageView iv = (ImageView) getActivity().findViewById(R.id.event_desc_image);
                    final String imgURL = "http://api-memberapp.csivit.com:8080"+event.getEventImage();
                    new DownLoadImageTask(iv).execute(imgURL);
                }
            }

            @Override
            public void onFailure(Call<Event> c,Throwable t){
            }
        });

    }

    public Event_desc_fragment(){

    }

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(String...urls){
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();

                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
            progressDialog.dismiss();
        }
    }
}
