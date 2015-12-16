package com.example.maggiemoheb.bugs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class LoginActivity extends ActionBarActivity {
    Button btnLogin;
    private LoginButton loginButton;
    private TextView info;
    private CallbackManager callbackManager;
    Button btnSignUp;
    private ProfileTracker profileTracker;
    boolean loggedIn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        callbackManager = CallbackManager.Factory.create();


        loginButton = (LoginButton) findViewById(R.id.btnFacebookLogin);
        loginButton.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends,user_location,user_hometown"));
        info = (TextView) findViewById(R.id.info);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp= (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = (EditText) findViewById(R.id.txtEmail);
                EditText password = (EditText) findViewById(R.id.txtPassword);
                CharSequence emailValidating = email.getText().toString();

                if (email.getText().toString().equals("") && password.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Make sure you have entered both your email and password", Toast.LENGTH_LONG).show();
                else if (email.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Make sure you have entered your email", Toast.LENGTH_LONG).show();
                else if (password.getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(), "Make sure you have entered your password", Toast.LENGTH_LONG).show();
                else if (!isEmailValid(emailValidating))
                    Toast.makeText(getApplicationContext(), "Make sure you have entered a valid email", Toast.LENGTH_LONG).show();
                else if (password.getText().toString().length() < 6)
                    Toast.makeText(getApplicationContext(), "your password couldn't be less than 6 characters", Toast.LENGTH_LONG).show();
                else if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {


                            final String userEmail = email.getText().toString();
                            final String userPassword= password.getText().toString();
                            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(getResources().getString(R.string.ENDPOINT)).build();
                                API api = adapter.create(API.class);
                                api.login(userEmail, userPassword, new Callback<User>() {
                                    @Override
                                public void success(User user, Response response) {

                                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putInt("userID", user.getUser_ID());
                                    editor.putString("firstName", user.getF_name());
                                    editor.putString("LastName", user.getL_name());
                                    editor.putString("password", userPassword);
                                    editor.putString("email", userEmail);
                                    editor.putString("loggedIn", "true");
                                    editor.commit();
                                    startActivity(new Intent(LoginActivity.this, NewsFeed.class));
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    if (error.getMessage().contains("401 Unauthorized")) {
                                        Toast.makeText(getApplicationContext(), "Wrong Email/Password", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Make sure you are online.\nIf this problem proceeds, contact us.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void FBlogin(View view) {
        if(!loggedIn){
            loggedIn=true;
        }
        else{
            loggedIn=false;
            logout();
            return;
        }
        info = (TextView) findViewById(R.id.info);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(final LoginResult loginResult) {

                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/friends",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
            /* handle the result */

                                info.setText(response.getRawResponse().toString());
                            }

                        }
                ).executeAsync();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
try {   String token = loginResult.getAccessToken().getToken();
        String email= object.getString("email");
        String first_name=object.getString("first_name");
        String last_name=object.getString("last_name");
        String gender=object.getString("gender");
        String birthdate=object.getString("birthday");
    String[] location= object.getJSONObject("location").getString("name").split(",");
    String city =location[0];
    String country =location[1];

    info.setText(email +"---"+first_name+"---"+last_name+"---"+gender+"---"+birthdate+"---"+city+"---"+country+"---"+token+"-----"+getProfilePicture(object.getString("id")));


}
catch(JSONException e) {
    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
}
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,gender,email, birthday,location,hometown");
                request.setParameters(parameters);
                request.executeAsync();
              /*  info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );*/
             //   startActivity(new Intent(getApplicationContext(), NewsFeed.class));

            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed. " +e.toString());

            }
        });
    }
    public static void logout(){
        LoginManager.getInstance().logOut();
    }
    public static String getProfilePicture(String userId) {

        String stringURL = null;
        try {
            stringURL = "http://graph.facebook.com/" + URLEncoder.encode(userId, "UTF-8") + "?fields=" + URLEncoder.encode("picture", "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }



        JSONObject jsonObject = null;
        String response = "";

        try {

            HttpGet get = new HttpGet(stringURL);
            get.setHeader("Content-Type", "text/plain; charset=utf-8");
            get.setHeader("Expect", "100-continue");

            HttpResponse resp = null;
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                resp = httpClient.execute(get);
            } catch (Exception e) {
                e.printStackTrace();

            }
            // get the response from the server and store it in result
            DataInputStream dataIn = null;
            try {
                //              dataIn = new DataInputStream(connection.getInputStream());
                if (resp != null) {
                    dataIn = new DataInputStream((resp.getEntity().getContent()));
                }
            }catch (Exception e) {
                e.printStackTrace();

            }

            if(dataIn != null){
                String inputLine;
                while ((inputLine = dataIn.readLine()) != null) {
                    response += inputLine;
                }



                if(response != null && !(response.trim().equals(""))) {
                    jsonObject = new JSONObject(response);
                }

                dataIn.close();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        String profilePicture = "";
        try{
            if(jsonObject != null){
                JSONObject jsonPicture = jsonObject.getJSONObject("picture");
                if(jsonPicture != null){
                    JSONObject jsonData = jsonPicture.getJSONObject("data");
                    if(jsonData != null){
                        profilePicture = jsonData.getString("url");
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return profilePicture;
    }

}
