package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.Follower;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class followers extends ListActivity {
    private int[] photos = new int[]{R.drawable.m};
    private ArrayList<String> userNames;
    private ArrayList<Integer> iconFollowers;
    private ArrayList<Integer> followersIds;
    private CustomListAdapter adapter2;
    ImageView logo;
    private Button shareButton;
    private CallbackManager callbackManager;
    String titles[] = {"Profile", "NewsFeed", "Friends","Notifications","Settings", "Logout"};
    int icons[] = {R.mipmap.profile, R.mipmap.newsfeed, R.mipmap.followees,R.mipmap.notification,R.mipmap.settings, R.mipmap.logout};
    String name;
    int profile = R.mipmap.bug;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
       // logo = (ImageView)findViewById(R.id.logo);
       // logo.setImageResource(R.mipmap.bug);
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        API api = adapter.create(API.class);
        followersIds = new ArrayList<Integer>();
        userNames = new ArrayList<String>();
        api.followers(1,new Callback<List<Follower>>() {

            @Override
            public void success(List<Follower> followers, Response response) {
                Iterator<Follower> followersNames = followers.iterator();
                while(followersNames.hasNext()) {
                    Follower temp = followersNames.next();
                    followersIds.add(temp.getFollower_id());
                //    userNames.add(temp.getFollower_id()+"");

                }
                API api = adapter.create(API.class);
                Log.i("followers.size", followersIds.size() + "");
                for(int i = 0; i<followersIds.size(); i++) {
                    api.getUser(followersIds.get(i), new Callback<User>() {
                        @Override
                        public void success(User user, Response response) {
                            userNames.add(user.getF_name() + " "+ user.getL_name());
                            Log.i("follower", user.getF_name() + " "+ user.getL_name());
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getApplicationContext(), "error in getting followees names", Toast.LENGTH_LONG).show();

                        }
                    });
                }

                iconFollowers = new ArrayList<Integer>();
                int i = followersIds.size() - 1;
                iconFollowers.add(photos[0]);

                adapter2 = new CustomListAdapter(followers.this, userNames, iconFollowers);
                setListAdapter(adapter2);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "error in getting followers ids"+ error, Toast.LENGTH_LONG).show();
            }
        });

   //     Iterator<User> iterator = followers.iterator();

        registerForContextMenu(getListView());

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        name = (mSharedPreference.getString("Name", ""));
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
        getMenuInflater().inflate(R.menu.menu_followers, menu);
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(followers.this, FollowersProfile.class));

    }

    public void onClick(View view) {
        startActivity(new Intent(followers.this, FollowersProfile.class));

    }

}
