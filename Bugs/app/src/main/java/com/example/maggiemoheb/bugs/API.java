package com.example.maggiemoheb.bugs;

import java.util.List;

import models.Comment;
import models.Follower;
import models.Post;
import models.Session;
import models.Setting;
import models.User;
import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Amir on 12/5/2015.
 */
public interface API {
    @FormUrlEncoded
    @POST("/session")
    void login(@Field("session[name]") String username, @Field("session[password]") String password, Callback<Session> callback);

    @FormUrlEncoded
    @POST("/users/{id}/posts/")
    void create_post(@Path("id") int id, @Field("user_dest_id") int user_dest_id,@Field("user_id") int user_id,@Field("title") String title,@Field("text") String body, Callback<Post> callback);

    @FormUrlEncoded
    @POST("/users/{user_id}/posts/{post_id}/comments/")
    void postComment(@Field("comment[text]" )String comment,@Field("comment[user_id]" )String user_id,@Field("comment[post_id]" )String post_id
            ,@Path("user_id")String userID,@Path("post_id")String postID,Callback<Comment>callback);

    @FormUrlEncoded
    @PUT("/users/{user_id}/")
    void updateFName(@Field("user[f_name]") String f_name,@Field("user[id]") String id,Callback<User> callback);

    @FormUrlEncoded
    @PUT("/users/{user_id}/")
    void updateLName(@Field("user[l_name]") String L_name,@Field("user[id]") String id,Callback<User> callback);

    @FormUrlEncoded
    @PUT("/users/{user_id}/")
    void updateBirthDate(@Field("user[date_of_birth]") String birth,@Field("user[id]") String id,Callback<User> callback);

    @FormUrlEncoded
    @PUT("/users/{user_id}/")
    void updateGender(@Field("user[gender]") String gender,@Field("user[id]") String id,Callback<User> callback);

    @FormUrlEncoded
    @PUT("/users/{user_id}/")
    void updateLocation(@Field("user[city]") String location,@Field("user[id]") String id,Callback<User> callback);

    @FormUrlEncoded
    @PUT("/users/{user_id}/")
    void updateEmail(@Field("user[email]") String email,@Field("user[id]") String id,Callback<User> callback);

    @DELETE("/session/{token}")
    void logout(@Path("token") String access_token, Callback<Session> callback);

    @DELETE("/users/{user_id}/followers/{follower:id}")
    void unfollow(@Path("user_id") String user_id,@Path("user_id") String follower_id, Callback<User> callback);

    @GET("/users/{userID}/followers/{id}")
    void getFollower(@Path("userID") String userID, @Path("id") String followerID, Callback<User> callback);

    @GET("/users/{userID}/followees/")
    void getFollowees(@Path("userID") String user_ID, Callback<List<Follower>>callback);

    @GET("/users/{userID}/posts/")
    void getposts(@Path("userID") String user_ID, Callback<List<Post>>callback);

    @GET("/users/{user_id}/followers")
    void followers(@Path("user_id") int id, Callback<List<Follower>> callback);

    @GET("/v/users/{user_id}/posts/{title}")
    void getPost(@Path("user_id") int id,@Path("title") String title, Callback<List<Post>> callback);

    @GET("/users/{user_id}/posts/{post_id}/comments")
    void getComment(@Path("user_id") int id,@Path("post_id") int postID, Callback<List<Comment>> callback);

    @GET("/v/users/{user_id}/posts/{post_id}/comments/{commenter_id}")
    void getCommenterName(@Path("user_id") int id,@Path("post_id") int post_id,@Path("commenter_id") int commenter_id, Callback<List<User>> callback);
    @GET("/users/{user_id}/followees")
    void followees(@Path("user_id") int id, Callback<List<Follower>> callback);

    @GET("/users/{id}")
    void getUser(@Path("id") int id, Callback <User> callback);

    @GET("/users/{id}/settings")
    void getUserSettings(@Path("id") int id, Callback<Setting> callback);

    @GET("/users")
    void getUsers(Callback<List<User>> callback);
}
