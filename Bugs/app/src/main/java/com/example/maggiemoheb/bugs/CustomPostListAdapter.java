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
 * Created by maggiemoheb on 11/30/15.
 */
public class CustomPostListAdapter extends ArrayAdapter<String> {
    private Activity context;
    private ArrayList<String> postTitles;
    private ArrayList<String> postImages;
    private ArrayList<String> postTexts;
    private ArrayList<String> postWriters;

    public CustomPostListAdapter(Activity context, ArrayList<String> postTitles, ArrayList<String> postImages, ArrayList<String> postTexts, ArrayList<String> postWriters) {
        super(context, R.layout.postlist, postTitles);
        this.context = context;
        this.postTitles = postTitles;
        this.postImages = postImages;
        this.postTexts = postTexts;
        this.postWriters = postWriters;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.postlist, null, true);
        TextView postWriter = (TextView) rowView.findViewById(R.id.postWriter);
        TextView postTitle = (TextView) rowView.findViewById(R.id.postTitle);
        ImageView postImage = (ImageView) rowView.findViewById(R.id.postImage);
        TextView postText = (TextView) rowView.findViewById(R.id.postText);

        postWriter.setText(postWriters.get(position));
        postTitle.setText(postTitles.get(position));
        Picasso.with(context).load(this.postImages.get(position)).placeholder(R.drawable.profilepic).error(R.drawable.profilepic).into(postImage);
        postText.setText(postTexts.get(position));

        return rowView;
    }

}
