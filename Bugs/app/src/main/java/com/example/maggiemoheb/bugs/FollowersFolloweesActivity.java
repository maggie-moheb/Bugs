package com.example.maggiemoheb.bugs;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class FollowersFolloweesActivity extends TabActivity {
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers_followees);
        tabHost=(TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec followers=tabHost.newTabSpec("Followers");
        TabHost.TabSpec followees = tabHost.newTabSpec("Followees");

        Log.i("Tab", followers.toString());

        followers.setIndicator("Followers");
        followers.setContent(new Intent(this, followers.class));

        followees.setIndicator("Followees");
        followees.setContent(new Intent(this, followees.class));

        tabHost.addTab(followers);
        tabHost.addTab(followees);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_followers_followees, menu);
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
