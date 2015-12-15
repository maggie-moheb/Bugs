package com.example.maggiemoheb.bugs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class CustomListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> itemName;
    private ArrayList<Integer> imgId;

    public ArrayList<String> getItemName() {
        return itemName;
    }

    public void setItemName(ArrayList<String> itemName) {
        this.itemName = itemName;
    }

    public ArrayList<Integer> getImgId() {
        return imgId;
    }

    public void setImgId(ArrayList<Integer> imgId) {
        this.imgId = imgId;
    }

    public CustomListAdapter(Activity context, ArrayList<String> itemName, ArrayList<Integer> imgId) {
        super(context, R.layout.mylist, itemName);
        this.context = context;
        this.itemName = itemName;
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

        charText = charText.toLowerCase(Locale.getDefault());
        itemName.clear();
        imgId.clear();

//        for (int pos = 0; pos < tempItemname.size(); pos++) {
//            String name = tempItemname.get(pos).toLowerCase();
//            if (name.startsWith(charText) || name.contains(" " + charText)) {
//                itemName.add(tempItemname.get(pos));
//                imgId.add(tempImgid.get(pos));
//            }
//        }
        notifyDataSetChanged();
    }
}
