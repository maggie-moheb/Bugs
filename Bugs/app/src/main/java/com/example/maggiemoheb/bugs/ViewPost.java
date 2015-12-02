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

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;

import models.Comment;
import models.Post;


public class ViewPost extends ListActivity {
    TextView postWriter;
    TextView postTitle;
    TextView postText;
    Button shareButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
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
        Post dummyPost = new Post(1, "Hello this is a new post for trial", "Hello this is a new post for trying again", "", 1, 1);
        postWriter.setText("Maggie");
        postTitle.setText(dummyPost.getTitle());
        postText.setText(dummyPost.getText());
        commentText = (EditText)findViewById(R.id.comment_text);
        commentButton = (Button) findViewById(R.id.comment_button);
        commentButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                commentText.setText("");
            }
        });
        imageCommenter = (ImageView) findViewById(R.id.imageCommenter);
        //imageCommenter.setImageResource(R.drawable.profilepic);
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.profilepic);
        roundedImage = new RoundImage(bm);
        imageCommenter.setImageDrawable(roundedImage);
        ArrayList<String> commentImages = new ArrayList<>();
        ArrayList<String> commentTexts = new ArrayList<>();
        ArrayList<String>commentWriters = new ArrayList<>();

        ArrayList<Comment>comments = new ArrayList<>();
        Comment dummyComment1 = new Comment(1, "Woowww this post is amazing", 3);
        commentWriters.add("Maggie");
        commentImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        comments.add(dummyComment1);
        Comment dummyComment2 = new Comment(3, "This post has changed my life", 5);
        commentWriters.add("Youmna");
        comments.add(dummyComment2);
        commentImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        for(int i = 0;i<comments.size(); i++) {
            commentTexts.add(comments.get(i).getText());
        }
        CustomCommentListAdapter adapter = new CustomCommentListAdapter(this, commentImages, commentTexts, commentWriters);
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

}
