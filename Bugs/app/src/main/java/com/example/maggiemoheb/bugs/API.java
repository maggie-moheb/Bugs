package com.example.maggiemoheb.bugs;
import java.util.List;
import models.User;
import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface API {

    @GET("/users/a/{email}/{password}")
    void login(@Path("email") String email,@Path("password") String password, Callback<User> callback);

    @FormUrlEncoded
    @POST("/users")
    void signup(@Field("user[email]") String email, @Field("user[password]") String password, @Field("user[f_name") String firstName, @Field("user[l_name") String lastName, Callback<User> callback);

    @GET("/users/{id}")
    void getUser(@Path("id") int id, Callback <User> callback);

    @GET("/users")
    void getUsers(Callback<List<User>> callback);
}
