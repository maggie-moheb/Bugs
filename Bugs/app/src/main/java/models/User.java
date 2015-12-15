package models;


import java.sql.Date;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class User {
    int id;
    String f_name;
    String l_name;
    String password;
    String email;
    String city;
    String country;
    Date date_of_birth;
    boolean gender;
    String profile_picture;
    String facebook_access_token;
    String access_token;

    public User() {
    }

    public User(String photo, String email, String password, String l_name, String f_name, int user_ID) {

        this.profile_picture = photo;
        this.email = email;
        this.password = password;
        l_name = l_name;
        f_name = f_name;
        this.id = user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.id = user_ID;
    }

    public void setF_name(String f_name) {
        f_name = f_name;
    }

    public void setL_name(String l_name) {
       l_name = l_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto(String photo) {
        this.profile_picture = photo;
    }

    public int getUser_ID() {

        return id;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return profile_picture;
    }
}
