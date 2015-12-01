package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;

import java.util.ArrayList;
import java.util.Iterator;

import models.User;


public class followees extends ListActivity {
    private int[] photos = new int[]{R.drawable.m};
    private ArrayList<String> userNames;
    private ArrayList<Integer> iconFollowers;
    private ArrayList<User> followers;
    private CustomListAdapter adapter2;
    ImageView logo;
    private Button shareButton;
    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followees);
        followers = new ArrayList<User>();
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());

        followers.add(new User("1","maggie@gmail.com","","","maggie",0));
        followers.add(new User("2", "youmnasalah@gmail.com", "", "", "youmna", 1));
        userNames = new ArrayList<String>();
        Iterator<User> iterator = followers.iterator();
        iconFollowers = new ArrayList<Integer>();
        int i = followers.size() - 1;
        while (i >= 0 & iterator.hasNext()) {
        userNames.add(iterator.next().getF_name());
        iconFollowers.add(photos[0]);
            i--;
        }
        adapter2 = new CustomListAdapter(followees.this, userNames, iconFollowers);
        setListAdapter(adapter2);
        registerForContextMenu(getListView());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_followees, menu);
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
