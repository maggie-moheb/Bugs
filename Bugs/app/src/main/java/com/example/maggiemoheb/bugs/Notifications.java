package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Iterator;

import models.Notification;


public class Notifications extends ListActivity {
    private CustomListAdapter adapter2;
    private  ArrayList<Notification> notifications;
    private int[] photos = new int[]{R.drawable.n};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        notifications = new ArrayList<Notification>();
        notifications.add(new Notification("1","2","Youmna does not like your post :D "));
        Iterator<Notification> iterator = notifications.iterator();
        ArrayList<String> notify = new ArrayList<String>();
        ArrayList<Integer> img = new ArrayList<Integer>();
        int i = notifications.size()- 1;
//        while (i >= 0 & iterator.hasNext()) {
        notify.add(iterator.next().getNotifying());
        img.add(photos[0]);
//            i--;
//        }
        adapter2 = new CustomListAdapter(Notifications.this, notify,img);
        setListAdapter(adapter2);
        registerForContextMenu(getListView());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
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
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        startActivity(new Intent(Notifications.this, ViewPost.class));
//
//    }
}
