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
    String gender;
    String profile_picture;
    String facebook_access_token;
    String access_token;



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(int user_ID, String f_name, String l_name, String password, String email, String photo, String city, String country, String gender, Date date_of_birth) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.password = password;
        this.email = email;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.date_of_birth = date_of_birth;
        this.profile_picture = photo;
        this.email = email;
        this.password = password;
        this.id = user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.id = user_ID;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
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
