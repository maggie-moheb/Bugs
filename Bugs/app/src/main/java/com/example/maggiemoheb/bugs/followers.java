package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.Iterator;

import models.User;


public class followers extends ListActivity {
    private int[] photos = new int[]{R.drawable.m};
    private ArrayList<String> userNames;
    private ArrayList<Integer> iconFollowers;
    private ArrayList<User> followers;
    private CustomListAdapter adapter2;
    private Button shareButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        followers = new ArrayList<User>();
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());

        followers.add(new User("1","maggie@gmail.com","","","maggie",0));

        userNames = new ArrayList<String>();
        Iterator<User> iterator = followers.iterator();
        iconFollowers = new ArrayList<Integer>();
        int i = followers.size() - 1;
//        while (i >= 0 & iterator.hasNext()) {
        userNames.add(iterator.next().getF_name());
        iconFollowers.add(photos[0]);
//            i--;
//        }
        adapter2 = new CustomListAdapter(followers.this, userNames, iconFollowers);
        setListAdapter(adapter2);
        registerForContextMenu(getListView());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_followers, menu);
        return true;
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
        startActivity(new Intent(followers.this, Profile.class));

    }
}
