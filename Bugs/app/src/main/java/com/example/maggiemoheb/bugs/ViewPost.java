package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

import models.Comment;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ViewPost extends ListActivity {
    TextView postWriter;
    TextView postTitle;
    TextView postText;
    ImageView postImage;
    Button shareButton;
    int post_id;
    int User_id;
    String titles[] = {"Profile", "NewsFeed", "Friends","Notifications","Settings", "Logout"};
    int icons[] = {R.mipmap.profile, R.mipmap.newsfeed, R.mipmap.followees,R.mipmap.notification,R.mipmap.settings, R.mipmap.logout};
    String name;
    int profile = R.mipmap.bug;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarDrawerToggle mDrawerToggle;
    EditText commentText;
    Button commentButton;
    ImageView imageCommenter;
    RoundImage roundedImage;
    RestAdapter adapter ;
    SharedPreferences mSharedPreference;
    SharedPreferences.Editor editor;
    ArrayList<String> commentImages;
    ArrayList<String> commentTexts;
    ArrayList<String>commentWriters;
    API api ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = mSharedPreference.edit();
        postTitle = (TextView)findViewById(R.id.postTitle);
        postText = (TextView)findViewById(R.id.postText);
        postWriter =(TextView)findViewById(R.id.postWriter);
        postImage = (ImageView)findViewById(R.id.postImage);
        User_id  = mSharedPreference.getInt("User_ID",1);
        postTitle.setText(mSharedPreference.getString("postName", " "));
        postText.setText(mSharedPreference.getString("postText", " "));
        postWriter.setText(mSharedPreference.getString("userName"," "));
//        InputStream is = null;
//        try {
//            is = (InputStream) new URL(mSharedPreference.getString("postImage","")).getContent();
//            Drawable d = Drawable.createFromStream(is, "srcName");
//            postImage.setImageDrawable(d);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        postWriter = (TextView) findViewById(R.id.postWriter);
        postTitle = (TextView) findViewById(R.id.postTitle);
        postText = (TextView) findViewById(R.id.postText);
        shareButton = (Button) findViewById(R.id.share_button);
        shareButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                FBshare(view);
            }
        });

        adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        commentText = (EditText)findViewById(R.id.comment_text);
        commentButton = (Button) findViewById(R.id.comment_button);
        commentButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                comment();
            }
        });
        imageCommenter = (ImageView) findViewById(R.id.imageCommenter);
        api = adapter.create(API.class);
        //imageCommenter.setImageResource(R.drawable.profilepic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profilepic);
        roundedImage = new RoundImage(bm);
        imageCommenter.setImageDrawable(roundedImage);
        commentImages = new ArrayList<String>();
        commentTexts = new ArrayList<String>();
        commentWriters = new ArrayList<String>();

        fillcommentList();

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
        getMenuInflater().inflate(R.menu.menu_view_post, menu);
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
    public void FBshare(View view) {
        ShareContent linkContent = new ShareLinkContent.Builder()
                .setContentDescription("https://developers.facebook.com/bugs/332619626816423")
                .setContentTitle("https://developers.facebook.com/bugs/332619626816423")
                .setContentUrl(Uri.parse("https://developers.facebook.com/bugs/332619626816423"))
                .build();
        ShareDialog s = new ShareDialog(this);
        s.show(linkContent);
    }

    public void fillcommentList(){
        post_id = mSharedPreference.getInt("postNumber",1);
        api.getComment(User_id,post_id,new Callback<List<Comment>>() {
            @Override
            public void success(List<Comment> commentsList, Response response) {
                for(int i = 0;i<commentsList.size();i++){
                   Comment current = commentsList.get(i);
                    commentTexts.add(i,current.getText());
                    final int x = i;
                    api.getCommenterName(User_id,post_id,current.getUser_id(),new Callback<List<User>>() {
                        @Override
                        public void success(List<User> users, Response response) {
                            commentWriters.add(x, users.get(0).getF_name() + "  " + users.get(0).getL_name());
                            commentImages.add(x, users.get(0).getPhoto());

                            CustomCommentListAdapter adapter = new CustomCommentListAdapter(ViewPost.this, commentImages, commentTexts, commentWriters);
                            setListAdapter(adapter);
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
    public void comment(){
        api.postComment(commentText.getText().toString(),User_id+"",post_id+"",User_id+"",post_id+"",new Callback<Comment>() {
            @Override
            public void success(Comment comment, Response response) {
                Toast.makeText(getApplicationContext(),"Comment delivered :D", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ViewPost.this, ViewPost.class));

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }

}
