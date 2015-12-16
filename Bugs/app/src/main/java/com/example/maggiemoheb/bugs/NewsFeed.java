package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import models.Follower;
import models.Post;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class NewsFeed extends ListActivity {
    private ArrayList<String> postTitles;
    private ArrayList<String> postImages;
    private ArrayList<String> postTexts;
    private ArrayList<String> postWriters;
    ImageView profilePic;
    ImageView logo;
    SharedPreferences mSharedPreference;
    API api;
    int user_ID;
    Button search;
    String titles[] = {"Profile", "NewsFeed", "Friends","Notifications","Settings", "Logout"};
    int icons[] = {R.mipmap.profile, R.mipmap.newsfeed, R.mipmap.followees,R.mipmap.notification,R.mipmap.settings, R.mipmap.logout};
    String name;
    int profile = R.mipmap.bug;
    RecyclerView mRecyclerView;
    TextView welcome;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    RoundImage roundedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        profilePic = (ImageView)findViewById(R.id.profilePic);
        welcome = (TextView)findViewById(R.id.welcomeText);
        search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(NewsFeed.this, Search.class));
            }
        });
       // profilePic.setImageResource(R.drawable.profilepic);
      //  profilePic = (ImageView)findViewById(R.id.profilePicture);
        // mImageView.setImageResource(R.drawable.profilepic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profilepic);
        roundedImage = new RoundImage(bm);
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        user_ID = (mSharedPreference.getInt("userID", 1));
        profilePic.setImageDrawable(roundedImage);
        profilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsFeed.this, Profile.class));

            }
        });
        logo = (ImageView)findViewById(R.id.logo);
        logo.setImageResource(R.mipmap.bug);
        fillFeed();
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        name = (mSharedPreference.getString("Name", ""));
        welcome.setText("Hello "+mSharedPreference.getString("userName"," ")+" !");
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
        getMenuInflater().inflate(R.menu.menu_news_feed, menu);
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
        final String title  = postTitles.get(position);
         api.getPost(user_ID,title,new Callback<List<Post>>() {
             @Override
             public void success(List<Post> posts, Response response) {
                  mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                 SharedPreferences.Editor editor = mSharedPreference.edit();
                 editor.putInt("postNumber", posts.get(0).getId());
                 editor.putString("postName", posts.get(0).getTitle());
                 editor.putString("postText",posts.get(0).getText());
                 editor.putString("postImage",posts.get(0).getPhoto());
                 editor.apply();
                 editor.commit();
                 startActivity(new Intent(NewsFeed.this, ViewPost.class));

             }

             @Override
             public void failure(RetrofitError error) {

             }
         });

    }
    public void fillFeed(){
        postTitles = new ArrayList<String>();
        postImages = new ArrayList<String>();
        postTexts = new ArrayList<String>();
        postWriters = new ArrayList<String>();
        final RestAdapter ADAPTER = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        api = ADAPTER.create(API.class);
        api.getFollowees(user_ID+"",new Callback<List<Follower>>() {
            @Override
            public void success(final List<Follower> users, Response response) {
                for(int i = 0; i<users.size(); i++) {
                    final int x = i;
                    api.getUser(users.get(i).getFollowee_id(),new Callback<User>() {
                        @Override
                        public void success(final User user, Response response) {
                            api.getposts(user.getUser_ID()+" ",new Callback<List<Post>>() {
                                @Override
                                public void success(List<Post> posts, Response response) {
                                    for(int k = 0;k < posts.size(); k++){
                                        Post current = posts.get(k);
                                        postWriters.add(k, user.getF_name() + " " + user.getL_name());
                                        postTexts.add(k,current.getText());
                                        postTitles.add(k,current.getTitle());
                                        postImages.add(k,current.getPhoto());

                                    }
                                    CustomPostListAdapter adapter = new CustomPostListAdapter(NewsFeed.this,postTitles, postImages, postTexts, postWriters);
                                    setListAdapter(adapter);
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });

                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

}
