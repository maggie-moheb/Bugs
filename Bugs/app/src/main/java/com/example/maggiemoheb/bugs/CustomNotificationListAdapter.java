package com.example.maggiemoheb.bugs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by maggiemoheb on 12/2/15.
 */
public class CustomNotificationListAdapter  extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> notificationTexts;
    public CustomNotificationListAdapter(Activity context,  ArrayList<String> notificationTexts) {
        super(context, R.layout.notificationlist, notificationTexts);
        this.context = context;
        this.notificationTexts = notificationTexts;
    }
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.notificationlist, null, true);
        TextView notificationText = (TextView)rowView.findViewById(R.id.notificationText);
        notificationText.setText(notificationTexts.get(position));
        return rowView;
    }
}
