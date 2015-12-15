package com.example.maggiemoheb.bugs;

import java.util.List;

import models.Comment;
import models.Follower;
import models.Post;
import models.Session;
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

    @FormUrlEncoded
    @POST("/users/{user_id}/posts/{post_id}/comments/")
    void postComment(@Field("comment[text]" )String comment,@Field("comment[user_id]" )String user_id,@Field("comment[post_id]" )String post_id
            ,@Path("user_id")String userID,@Path("post_id")String postID,Callback<Comment>callback);


    @DELETE("/session/{token}")
    void logout(@Path("token") String access_token, Callback<Session> callback);

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

    @GET("/users/{id}")
    void getUser(@Path("id") int id, Callback <User> callback);
}
