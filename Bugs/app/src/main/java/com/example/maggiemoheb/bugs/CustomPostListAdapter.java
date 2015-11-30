package com.example.maggiemoheb.bugs;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
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
    private Button shareButton;

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
        shareButton = (Button)rowView.findViewById(R.id.share_button);
        shareButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                FBshare(v);
            }
        });
        postWriter.setText(postWriters.get(position));
        postTitle.setText(postTitles.get(position));
        Picasso.with(context).load(this.postImages.get(position)).placeholder(R.drawable.profilepic).error(R.drawable.profilepic).into(postImage);
        postText.setText(postTexts.get(position));

        return rowView;
    }
    public void FBshare(View view) {

        ShareContent linkContent = new ShareLinkContent.Builder()
                .setContentDescription("https://developers.facebook.com/bugs/332619626816423")
                .setContentTitle("https://developers.facebook.com/bugs/332619626816423")
                .setContentUrl(Uri.parse("https://developers.facebook.com/bugs/332619626816423"))
                .build();
//        ShareDialog s = new ShareDialog(NewsFeed.class);
//        s.show(linkContent);
    }
}
