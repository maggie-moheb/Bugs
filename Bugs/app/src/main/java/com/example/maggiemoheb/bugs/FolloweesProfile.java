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
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class FolloweesProfile extends ListActivity {
    private ImageView mImageView;
    private Button newPost;
    private ArrayList<String> postTitles;
    private ArrayList<String> postImages;
    private ArrayList<String> postTexts;
    private ArrayList<String> postWriters;
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
        setContentView(R.layout.activity_followees_profile);
        mImageView = (ImageView)findViewById(R.id.profilePicture);
        mImageView.setImageResource(R.drawable.profilepic);

        newPost = (Button)findViewById(R.id.newPostButton);
        newPost.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FolloweesProfile.this, CreatePost.class));
            }
        });
        postTitles = new ArrayList<>();
        postImages = new ArrayList<>();
        postTexts = new ArrayList<>();
        postWriters = new ArrayList<>();
        postTitles.add("Apple Macintosh has completed 30 years");
        postImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        postTexts.add("I cannot believe apple macintosh has completed 30 years, I was born before it by about 50 years, and now everybody is just using the computer");
        postWriters.add("Maggie Moheb");
        postTitles.add("Mark Zuckerberg has finally bought whatsapp");
        postImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        postTexts.add("I cannot believe apple macintosh has completed 30 years, I was born before it by about 50 years, and now everybody is just using the computer");
        postWriters.add("Maggie Moheb");
        CustomPostListAdapter adapter = new CustomPostListAdapter(FolloweesProfile.this, this.postTitles, this.postImages, this.postTexts, this.postWriters);
        setListAdapter(adapter);

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
        getMenuInflater().inflate(R.menu.menu_followees_profile, menu);
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
        startActivity(new Intent(FolloweesProfile.this, CreatePost.class));
    }
}
