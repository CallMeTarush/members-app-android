package com.csivit.tarush.csi_membersapp.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.csivit.tarush.csi_membersapp.CustomAdapters.MyRecyclerViewAdapter;
import com.csivit.tarush.csi_membersapp.Fragments.Blog_fragment;
import com.csivit.tarush.csi_membersapp.Fragments.Chat_fragment;
import com.csivit.tarush.csi_membersapp.Fragments.Event_desc_fragment;
import com.csivit.tarush.csi_membersapp.Fragments.Events_fragment;
import com.csivit.tarush.csi_membersapp.Fragments.Help_fragment;
import com.csivit.tarush.csi_membersapp.R;
import com.csivit.tarush.csi_membersapp.model.system.Event;
import com.csivit.tarush.csi_membersapp.model.system.User;
import com.csivit.tarush.csi_membersapp.service.DataStore;
import com.csivit.tarush.csi_membersapp.service.MembersAPI;
import com.csivit.tarush.csi_membersapp.service.MembersService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.action;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.PassEvent
         {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

                        //  Initialize SharedPreferences
        SharedPreferences getPrefs = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());

        boolean loginReq = getPrefs.getBoolean("loginReq", true);

        //Check if logged in
        if (loginReq) {
            Intent i = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(i);
                    MainActivity.this.finish();


                }
        else {
            String token = getPrefs.getString("token", "");
            DataStore.getInstance().setJwtToken(token);
            setDataStoreUser();

            setContentView(R.layout.activity_main);

            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.action_bar);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#181c4f")));

            int[] icons = {R.drawable.events,
                    R.drawable.blog,
                    R.drawable.chat,
                    R.drawable.help
            };


            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText("Events"));
            tabLayout.addTab(tabLayout.newTab().setText("Blog"));
            tabLayout.addTab(tabLayout.newTab().setText("Chat"));
            tabLayout.addTab(tabLayout.newTab().setText("Help"));

            for (int i = 0; i < icons.length; i++) {
                tabLayout.getTabAt(i).setIcon(icons[i]);
            }
            setActionBarTitle("Events");
            replaceFragment(new Events_fragment());

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0) {
                        setActionBarTitle("Events");
                        replaceFragment(new Events_fragment());
                    } else if (tab.getPosition() == 1) {
                        setActionBarTitle("Blog");
                        replaceFragment(new Blog_fragment());
                    } else if (tab.getPosition() == 2) {
                        setActionBarTitle("Chat");
                        replaceFragment(new Chat_fragment());
                    } else {
                        setActionBarTitle("Help");
                        replaceFragment(new Help_fragment());
                    }

                }


                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
    }

    private void setDataStoreUser(){
        MembersAPI membersAPI = new MembersService().getAPI();
        Call<User> loggedInUser = membersAPI.getUser();
        loggedInUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code() == 200){
                    User current = response.body();
                    DataStore.getInstance().setRegisteringUser(current);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

    }

            @Override
            public void eventPasser(String id){
                Event_desc_fragment e = new Event_desc_fragment();
                e.loadEvent(id);
                replaceFragment(e);

            }

             public void setActionBarTitle(String title) {
                 TextView action = (TextView) findViewById(R.id.mytext);
                 action.setText(title.toString());
             }

             public void replaceFragment(Fragment fragment) {
                 FragmentManager fragmentManager = getSupportFragmentManager();
                 android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                 transaction.replace(R.id.fragment_container, fragment);
                 transaction.addToBackStack("tag");
                 transaction.commit();
             }

         }

