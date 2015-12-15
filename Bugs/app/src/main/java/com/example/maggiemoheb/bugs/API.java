package com.example.maggiemoheb.bugs;

import java.util.List;

import models.Follower;
import models.Session;
import models.Setting;
import models.User;
import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Amir on 12/5/2015.
 */
public interface API {
    @FormUrlEncoded
    @POST("/session")
    void login(@Field("session[name]") String username, @Field("session[password]") String password, Callback<Session> callback);

    @DELETE("/session/{token}")
    void logout(@Path("token") String access_token, Callback<Session> callback);

    @GET("/users/{userID}/followers/{id}")
    void getFollower(@Path("userID") String userID, @Path("id") String followerID, Callback<User> callback);

    @GET("/users/{user_id}/followers")
    void followers(@Path("user_id") int id, Callback<List<Follower>> callback);

    @GET("/users/{id}")
    void getUser(@Path("id") int id, Callback <User> callback);

    @GET("/users/{id}/settings")
    void getUserSettings(@Path("id") int id, Callback<Setting> callback);
}
