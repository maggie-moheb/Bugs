package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
