package com.example.maggiemoheb.bugs;
import models.Session;
import retrofit.http.DELETE;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Field;
import retrofit.http.POST;
import retrofit.Callback;
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
}
