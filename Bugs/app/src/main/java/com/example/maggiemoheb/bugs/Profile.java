package com.example.maggiemoheb.bugs;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Profile extends ListActivity {

    private ImageView mImageView;

    private Button newPost;

    private TextView name;
    private EditText editName;

    private TextView email;
    private EditText editEmail;

    private TextView location;
    private EditText editLocation;

    private TextView birthDate;
    private EditText editBirthDate;

    private TextView gender;
    private EditText editGender;

    private ArrayList<String> postTitles;
    private ArrayList<String> postImages;
    private ArrayList<String> postTexts;
    private ArrayList<String> postWriters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//bind imageview with your xml's id

        mImageView = (ImageView)findViewById(R.id.profilePicture);
        mImageView.setImageResource(R.drawable.profilepic);

        name = (TextView)findViewById(R.id.name);
        editName = (EditText)findViewById(R.id.editName);
        name.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {

                    name.setVisibility(View.INVISIBLE);
                    editName.setVisibility(View.VISIBLE);
                    editName.setText(name.getText());
                    return true;
                }
        });
        editName.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                name.setText(editName.getText());
                name.setVisibility(View.VISIBLE);
                editName.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        email = (TextView)findViewById(R.id.email);
        editEmail = (EditText)findViewById(R.id.editEmail);
        email.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {

                email.setVisibility(View.INVISIBLE);
                editEmail.setText(email.getText());
                editEmail.setVisibility(View.VISIBLE);
                editEmail.setText(email.getText());
                return true;
            }
        });
        editEmail.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                email.setText(editEmail.getText());
                email.setVisibility(View.VISIBLE);
                editEmail.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        location = (TextView)findViewById(R.id.location);
        editLocation = (EditText)findViewById(R.id.editLocation);
        location.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                location.setVisibility(View.INVISIBLE);
                editLocation.setText(location.getText());
                editLocation.setVisibility(View.VISIBLE);
                return true;
            }
        });
        editLocation.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                location.setText(editLocation.getText());
                location.setVisibility(View.VISIBLE);
                editLocation.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        birthDate = (TextView)findViewById(R.id.birthDate);
        editBirthDate = (EditText)findViewById(R.id.editBirthDate);
        birthDate.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                birthDate.setVisibility(View.INVISIBLE);
                editBirthDate.setText(birthDate.getText());
                editBirthDate.setVisibility(View.VISIBLE);
                return true;
            }
        });
        editBirthDate.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                birthDate.setText(birthDate.getText());
                birthDate.setVisibility(View.VISIBLE);
                editBirthDate.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        gender = (TextView) findViewById(R.id.gender);
        editGender = (EditText) findViewById(R.id.editGender);
        gender.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                gender.setVisibility(View.INVISIBLE);
                editGender.setVisibility(View.VISIBLE);
                return true;
            }
        });
        editGender.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                gender.setText(editGender.getText());
                editGender.setText(gender.getText());
                gender.setVisibility(View.VISIBLE);
                editGender.setVisibility(View.INVISIBLE);
                return true;
            }
        });
        newPost = (Button)findViewById(R.id.newPostButton);
        newPost.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, CreatePost.class));
            }
        });
        postTitles = new ArrayList<>();
        postImages = new ArrayList<>();
        postTexts = new ArrayList<>();
        postWriters = new ArrayList<>();
        postTitles.add("Apple Macintosh has completed 30 years");
        postImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        postTexts.add("I cannot believe apple macintosh has completed 30 years, I was born before it by about 50 years, and now everybody is just using the computer");
        postWriters.add("Maggie Moheb");
        postTitles.add("Mark Zuckerberg has finally bought whatsapp");
        postImages.add("http://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2014/1/24/1390579173532/a52a44b2-7a7d-44ca-804f-f3648f3bd595-620x461.jpeg");
        postTexts.add("I cannot believe apple macintosh has completed 30 years, I was born before it by about 50 years, and now everybody is just using the computer");
        postWriters.add("Maggie Moheb");
        CustomPostListAdapter adapter = new CustomPostListAdapter(Profile.this, this.postTitles, this.postImages, this.postTexts, this.postWriters);
        setListAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        startActivity(new Intent(Profile.this, CreatePost.class));
    }
}
