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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import models.Post;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Profile extends ListActivity {

    private ImageView mImageView;
    private Button newPost;
    private int user_id;
    private API api;
    private SharedPreferences mSharedPreference;

    private TextView name;
    private EditText editName;

    private TextView email;
    private EditText editEmail;

    private TextView location;
    private EditText editLocation;

    private TextView birthDate;
    private EditText editBirthDate;

    private TextView gender;
    private EditText editGender;

    ImageView logo;
    private ArrayList<String> postTitles;
    private ArrayList<String> postImages;
    private ArrayList<String> postTexts;
    private ArrayList<String> postWriters;

    String titles[] = {"Profile", "NewsFeed", "Friends","Notifications","Settings", "Logout"};
    int icons[] = {R.mipmap.profile, R.mipmap.newsfeed, R.mipmap.followees,R.mipmap.notification,R.mipmap.settings, R.mipmap.logout};
    String userName;
    int profile = R.mipmap.bug;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    RoundImage roundedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        user_id = mSharedPreference.getInt("User_id", 1);
        final RestAdapter ADAPTER =
                new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        api = ADAPTER.create(API.class);
        mImageView = (ImageView)findViewById(R.id.profilePicture);
       // mImageView.setImageResource(R.drawable.profilepic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profilepic);
        roundedImage = new RoundImage(bm);
        mImageView.setImageDrawable(roundedImage);
        logo = (ImageView)findViewById(R.id.logo);
        logo.setImageResource(R.mipmap.bug);

        name = (TextView)findViewById(R.id.name);
        editName = (EditText)findViewById(R.id.editName);
        name.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {

                    name.setVisibility(View.INVISIBLE);
                    editName.setVisibility(View.VISIBLE);
                    editName.setText(name.getText());
                updateFName(editName.getText().toString());
                return true;
                }
        });
        editName.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                name.setText(editName.getText());
                name.setVisibility(View.VISIBLE);
                editName.setVisibility(View.INVISIBLE);
                updateFName(editName.getText().toString());
                return true;
            }
        });

        email = (TextView)findViewById(R.id.email);
        editEmail = (EditText)findViewById(R.id.editEmail);
        email.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {

                email.setVisibility(View.INVISIBLE);
                editEmail.setText(email.getText());
                editEmail.setVisibility(View.VISIBLE);
                editEmail.setText(email.getText());
                updateEmail(editEmail.getText().toString());
                return true;
            }
        });
        editEmail.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                email.setText(editEmail.getText());
                email.setVisibility(View.VISIBLE);
                editEmail.setVisibility(View.INVISIBLE);
                updateEmail(editEmail.getText().toString());
                return true;
            }
        });

        location = (TextView)findViewById(R.id.location);
        editLocation = (EditText)findViewById(R.id.editLocation);
        location.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                location.setVisibility(View.INVISIBLE);
                editLocation.setText(location.getText());
                editLocation.setVisibility(View.VISIBLE);
                updateLocation(editLocation.getText().toString());
                return true;
            }
        });
        editLocation.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                location.setText(editLocation.getText());
                location.setVisibility(View.VISIBLE);
                editLocation.setVisibility(View.INVISIBLE);
                updateLocation(editLocation.getText().toString());
                return true;
            }
        });

        birthDate = (TextView)findViewById(R.id.birthDate);
        editBirthDate = (EditText)findViewById(R.id.editBirthDate);
        birthDate.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                birthDate.setVisibility(View.INVISIBLE);
                editBirthDate.setText(birthDate.getText());
                editBirthDate.setVisibility(View.VISIBLE);
                updateBirth(birthDate.getText().toString());
                return true;
            }
        });
        editBirthDate.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                birthDate.setText(birthDate.getText());
                birthDate.setVisibility(View.VISIBLE);
                editBirthDate.setVisibility(View.INVISIBLE);
                updateBirth(birthDate.getText().toString());
                return true;
            }
        });

        gender = (TextView) findViewById(R.id.gender);
        editGender = (EditText) findViewById(R.id.editGender);
        gender.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                gender.setVisibility(View.INVISIBLE);
                editGender.setVisibility(View.VISIBLE);
                editGender.setText(gender.getText());
                updateGender(gender.getText().toString());
                return true;
            }
        });
        editGender.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                gender.setText(editGender.getText());
                editGender.setText(gender.getText());
                gender.setVisibility(View.VISIBLE);
                editGender.setVisibility(View.INVISIBLE);
                updateGender(gender.getText().toString());
                return true;
            }
        });
        fillProfileData();

        newPost = (Button)findViewById(R.id.newPostButton);
        newPost.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, CreatePost.class));
            }
        });
        postTitles = new ArrayList<>();
        postImages = new ArrayList<>();
        postTexts = new ArrayList<>();
        postWriters = new ArrayList<>();
//        postTitles.add("Apple Macintosh has completed 30 years");
//        postImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
//        postTexts.add("I cannot believe apple macintosh has completed 30 years, I was born before it by about 50 years, and now everybody is just using the computer");
//        postWriters.add("Maggie Moheb");
//        postTitles.add("Mark Zuckerberg has finally bought whatsapp");
//        postImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
//        postTexts.add("I cannot believe apple macintosh has completed 30 years, I was born before it by about 50 years, and now everybody is just using the computer");
//        postWriters.add("Maggie Moheb");
        getPosts();

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        final SharedPreferences mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        userName = (mSharedPreference.getString("Name", ""));
        mAdapter = new SlideBarAdapter(titles, icons, userName, profile, this);
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
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
        api.getPost(user_id, title, new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor editor = mSharedPreference.edit();
                editor.putInt("postNumber", posts.get(0).getId());
                editor.putString("postName", posts.get(0).getTitle());
                editor.putString("postText", posts.get(0).getText());
                editor.putString("postImage", posts.get(0).getPhoto());
                editor.apply();
                editor.commit();
                startActivity(new Intent(Profile.this, ViewPost.class));

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void fillProfileData(){
        api.getUser(user_id,new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                name.setText(user.getF_name()+" "+user.getL_name());
                email.setText(user.getEmail());
                location.setText(user.getCity()+" "+user.getCountry());
                gender.setText((user.getGender().equals("true"))?"male":"female");
                birthDate.setText(user.getDate_of_birth()+" ");
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public  void getPosts(){
        api.getposts(user_id+"",new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                for(int i = 0;i<posts.size();i++){
                    Post current = posts.get(i);
                    postImages.add(i,current.getPhoto());
                    postTexts.add(i,current.getText());
                    postTitles.add(i,current.getTitle());
                    postWriters.add(i,name.getText().toString());
                }
                CustomPostListAdapter adapter = new CustomPostListAdapter(Profile.this, postTitles,
                        postImages,postTexts,postWriters);
                setListAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void updateFName(String s){
        api.updateFName(s,user_id+"", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Toast.makeText(getApplicationContext(),"Data Updated correctly", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Profile.this, Profile.class));
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void updateEmail(String s){
        api.updateEmail(s, user_id + "", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Toast.makeText(getApplicationContext(), "Data Updated correctly", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Profile.this, Profile.class));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void updateLocation(String s){
        api.updateLocation(s, user_id + "", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Toast.makeText(getApplicationContext(), "Data Updated correctly", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Profile.this, Profile.class));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void updateBirth(String s){
        api.updateBirthDate(s, user_id + "", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Toast.makeText(getApplicationContext(), "Data Updated correctly", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Profile.this, Profile.class));
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void updateGender(String s){
        api.updateGender((s.compareToIgnoreCase("male") == 0)?"true":"false",user_id+"", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Toast.makeText(getApplicationContext(),"Data Updated correctly", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Profile.this, Profile.class));
            }
            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
