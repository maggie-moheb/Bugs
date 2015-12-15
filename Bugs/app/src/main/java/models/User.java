package models;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class User {
    int user_ID;
    String f_name;
    String l_name;
    String password;
    String email;
    String photo;
    String city;
    String country;
    String gender;
    boolean can_post;

    public boolean isCan_post() {
        return can_post;
    }

    public void setCan_post(boolean can_post) {
        this.can_post = can_post;
    }

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

    String date_of_birth;

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(int user_ID, String f_name, String l_name, String password, String email, String photo, String city, String country, String gender, boolean can_post, String date_of_birth) {
        this.user_ID = user_ID;
        this.f_name = f_name;
        this.l_name = l_name;
        this.password = password;
        this.email = email;
        this.photo = photo;
        this.city = city;
        this.country = country;
        this.gender = gender;
        this.can_post = can_post;
        this.date_of_birth = date_of_birth;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
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
        this.photo = photo;
    }

    public int getUser_ID() {return user_ID; }

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
        return photo;
    }
}
