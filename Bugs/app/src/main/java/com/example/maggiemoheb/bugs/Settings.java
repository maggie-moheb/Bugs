package com.example.maggiemoheb.bugs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import models.Setting;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Settings extends ActionBarActivity {
    String titles[] = {"Profile", "NewsFeed", "Friends","Notifications","Settings", "Logout"};
    int icons[] = {R.mipmap.profile, R.mipmap.newsfeed, R.mipmap.followees,R.mipmap.notification,R.mipmap.settings, R.mipmap.logout};
    String name;
    int profile = R.mipmap.bug;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    Switch allowPost;
    Switch notifyComments;
    Switch notifyPost;
    Switch notifyFollow;
    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        name = (mSharedPreference.getString("Name", ""));
        allowPost = (Switch) findViewById(R.id.allow_post);
        notifyComments = (Switch) findViewById(R.id.notify_comments);
        notifyPost = (Switch) findViewById(R.id.notify_posts);
        notifyFollow = (Switch) findViewById(R.id.notify_follow);
        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        API api = adapter.create(API.class);
        api.getUserSettings(1, new Callback<Setting>() {
            @Override
            public void success(Setting setting, Response response) {
                Log.i("setting allow", setting.isFollowers_can_post()+"");
                if(setting.isFollowers_can_post()) {
                    allowPost.setChecked(true);
                }else {
                    allowPost.setChecked(false);
                }
                if(setting.isNotify_comments()) {
                    notifyComments.setChecked(true);
                }else{
                    notifyComments.setChecked(false);
                }
                if(setting.isNotify_followers()) {
                    notifyFollow.setChecked(true);
                }else{
                    notifyFollow.setChecked(false);
                }
                if(setting.isNotify_post()) {
                    notifyPost.setChecked(true);
                }else{
                    notifyPost.setChecked(false);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "Error in retrieving you settings!", Toast.LENGTH_LONG).show();
            }
        });
        mAdapter = new SlideBarAdapter(titles, icons, name, profile, this);
        mRecyclerView.setAdapter(mAdapter);
        final GestureDetector mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    Drawer.closeDrawers();
                    switch (recyclerView.getChildPosition(child)) {
                        case 1:
                            startActivity(new Intent(getApplicationContext(), Profile.class));
                            break;
                        case 2:
                            startActivity(new Intent(getApplicationContext(), NewsFeed.class));
                            break;
                        case 3:
                            startActivity(new Intent(getApplicationContext(), FollowersFolloweesActivity.class));
                            break;
                        case 4:
                            startActivity(new Intent(getApplicationContext(),Notifications.class));
                            break;
                        case 5:
                            startActivity(new Intent(getApplicationContext(),Settings.class));
                            break;
                        case 6:
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            break;
                    }
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
