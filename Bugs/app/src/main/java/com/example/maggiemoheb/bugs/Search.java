package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class Search extends ListActivity {
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchBar = (EditText) findViewById(R.id.search_bar);
        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
        API api = adapter.create(API.class);
        final ArrayList<String> usersNames = new ArrayList<>();
        api.getUsers(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Iterator<User> usersObjects = users.iterator();
                while(usersObjects.hasNext()) {
                    User temp = usersObjects.next();
                    usersNames.add(temp.getF_name()+" "+temp.getL_name());
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        Log.i("size of users:", usersNames.size()+"");
        CustomListAdapter adapter2 = new CustomListAdapter(Search.this, usersNames, new ArrayList<Integer>());
        setListAdapter(adapter2);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
