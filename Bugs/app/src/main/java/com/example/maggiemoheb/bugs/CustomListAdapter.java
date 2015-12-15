package com.example.maggiemoheb.bugs;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> itemName;
    private ArrayList<Integer> imgId;
    private ArrayList<String> tempItemName= new ArrayList<String>();
    public ArrayList<String> getItemName() {
        return itemName;
    }


    public CustomListAdapter(Activity context, ArrayList<String> itemName, ArrayList<Integer> imgId) {
        super(context, R.layout.mylist, itemName);
        this.context = context;
        this.itemName = itemName;
        this.tempItemName.addAll(this.itemName);
        Log.i("size in the beginning",this.itemName.size() + "");
        this.imgId = imgId;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.namefollower);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imagefollower);
        final Button followButton = (Button)rowView.findViewById(R.id.followButton);
        followButton.setOnClickListener( new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(followButton.getText() == "Unfollow") {
                    followButton.setText("Follow");
                    Toast.makeText(context,"You have successfully followed this person", Toast.LENGTH_LONG).show();
                } else {
                    followButton.setText("Unfollow");
                    Toast.makeText(context,"You have successfully unfollowed this person", Toast.LENGTH_LONG).show();

                }
            }
        });
        txtTitle.setText(itemName.get(position));
//        imageView.setImageResource(imgId.get(position));
        return rowView;
    }


    /**
     * Filter the list of rooms (itemName) matching a certain word
     *
     * @param charText string to filter with
     */

    public void filter(String charText) {
        final RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://10.0.2.2:3000").build();
        API api = adapter.create(API.class);
        api.getUsers(new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                Iterator<User> usersObjects = users.iterator();
                while(usersObjects.hasNext()) {
                    User temp = usersObjects.next();
                    tempItemName.add(temp.getF_name() + " " + temp.getL_name());
                }
            }



            @Override
            public void failure(RetrofitError error) {

            }
        });
        charText = charText.toLowerCase(Locale.getDefault());
        itemName.clear();
        for (int pos = 0; pos < tempItemName.size(); pos++) {
            String name = tempItemName.get(pos).toLowerCase();
            if (name.startsWith(charText) || name.contains(" " + charText)) {
                itemName.add(tempItemName.get(pos));
            }
        }
        tempItemName.clear();
        notifyDataSetChanged();
    }

}
