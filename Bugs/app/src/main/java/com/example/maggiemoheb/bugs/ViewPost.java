package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;

import models.Comment;
import models.Post;


public class ViewPost extends ListActivity {
    TextView postWriter;
    TextView postTitle;
    TextView postText;
    Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        postWriter = (TextView) findViewById(R.id.postWriter);
        postTitle = (TextView) findViewById(R.id.postTitle);
        postText = (TextView) findViewById(R.id.postText);
        shareButton = (Button) findViewById(R.id.share_button);
        shareButton.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                FBshare(view);
            }
        });
        Post dummyPost = new Post(1, "Hello this is a new post for trial", "Hello this is a new post for trying again", "", 1, 1);

        postWriter.setText("Maggie");
        postTitle.setText(dummyPost.getTitle());
        postText.setText(dummyPost.getText());
        ArrayList<String> commentImages = new ArrayList<>();
        ArrayList<String> commentTexts = new ArrayList<>();
        ArrayList<String>commentWriters = new ArrayList<>();

        ArrayList<Comment>comments = new ArrayList<>();
        Comment dummyComment1 = new Comment(1, "Woowww this post is amazing", 3);
        commentWriters.add("Maggie");
        commentImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        comments.add(dummyComment1);
        Comment dummyComment2 = new Comment(3, "This post has changed my life", 5);
        commentWriters.add("Youmna");
        comments.add(dummyComment2);
        commentImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        for(int i = 0;i<comments.size(); i++) {
            commentTexts.add(comments.get(i).getText());
        }
        CustomCommentListAdapter adapter = new CustomCommentListAdapter(this, commentImages, commentTexts, commentWriters);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_post, menu);
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
    public void FBshare(View view) {
        ShareContent linkContent = new ShareLinkContent.Builder()
                .setContentDescription("https://developers.facebook.com/bugs/332619626816423")
                .setContentTitle("https://developers.facebook.com/bugs/332619626816423")
                .setContentUrl(Uri.parse("https://developers.facebook.com/bugs/332619626816423"))
                .build();
        ShareDialog s = new ShareDialog(this);
        s.show(linkContent);
    }

}
