package com.example.maggiemoheb.bugs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by maggiemoheb on 12/1/15.
 */
public class CustomCommentListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> postTitles;
    private ArrayList<String> commentImages;
    private ArrayList<String> commentTexts;
    private ArrayList<String> commentWriters;


    public CustomCommentListAdapter(Activity context, ArrayList<String> commentImages, ArrayList<String> commentTexts, ArrayList<String> commentWriters) {
        super(context, R.layout.mylist, commentWriters);
        this.context = context;
        this.commentImages = commentImages;
        this.commentTexts = commentTexts;
        this.commentWriters = commentWriters;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);
        TextView commentBody = (TextView)rowView.findViewById(R.id.namefollower);
        ImageView commenterImage = (ImageView) rowView.findViewById(R.id.imagefollower);
        commentBody.setText(commentWriters.get(position)+": "+commentTexts.get(position));
        Picasso.with(context).load(this.commentImages.get(position)).placeholder(R.drawable.profilepic).error(R.drawable.profilepic).into(commenterImage);
        return rowView;

    }
}