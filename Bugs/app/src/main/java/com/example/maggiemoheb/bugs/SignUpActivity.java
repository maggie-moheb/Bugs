package com.example.maggiemoheb.bugs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SignUpActivity extends ActionBarActivity {
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText firstName = (EditText) findViewById(R.id.txtFirstName);
                EditText lastName = (EditText) findViewById(R.id.txtLastName);
                EditText email = (EditText) findViewById(R.id.txtEmail);
                EditText password = (EditText) findViewById(R.id.txtPassword);
                CharSequence emailValidating = email.getText().toString();

                if (email.getText().toString().equals("") || password.getText().toString().equals("") || firstName.getText().toString().equals("") || lastName.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Make sure you have entered all required information", Toast.LENGTH_LONG).show();
                else if(!isEmailValid(emailValidating))
                    Toast.makeText(getApplicationContext(), "Make sure you have entered a valid email", Toast.LENGTH_LONG).show();
                else if (password.getText().toString().length()<6)
                    Toast.makeText(getApplicationContext(), "your password couldn't be less than 6 characters", Toast.LENGTH_LONG).show();
                else if(!email.getText().toString().equals("") && !password.getText().toString().equals("") && !firstName.getText().toString().equals("") && !lastName.getText().toString().equals("")){
                    final String userEmail = email.getText().toString();
                    final String userPassword= password.getText().toString();
                    final String userFirstName=firstName.getText().toString();
                    final String userLastName= lastName.getText().toString();
                    RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
                    API api = adapter.create(API.class);

                    api.signup(userEmail, userPassword,userFirstName,userLastName, new Callback<User>() {
                        @Override
                        public void success(User user, Response response) {

                            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SignUpActivity.this);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("userID", user.getUser_ID());
                            editor.putString("firstName", userFirstName);
                            editor.putString("LastName", userLastName);
                            editor.putString("password", userPassword);
                            editor.putString("email", userEmail);
                            editor.putString("loggedIn", "true");
                            editor.commit();
                            startActivity(new Intent(SignUpActivity.this, Profile.class));
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            if (error.getMessage().contains("401 Unauthorized")) {
                                Toast.makeText(getApplicationContext(), "Email already exists", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Make sure you are online.\nIf this problem proceeds, contact us.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                   // Toast.makeText(getApplicationContext(), "Hello "+firstName.getText()+"!! You have signed up successfully", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
