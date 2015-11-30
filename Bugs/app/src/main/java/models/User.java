package models;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class User {
    int user_ID;
    String F_name;
    String L_name;
    String password;
    String email;
    String photo;

    public User() {
    }

    public User(String photo, String email, String password, String l_name, String f_name, int user_ID) {

        this.photo = photo;
        this.email = email;
        this.password = password;
        L_name = l_name;
        F_name = f_name;
        this.user_ID = user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public void setF_name(String f_name) {
        F_name = f_name;
    }

    public void setL_name(String l_name) {
        L_name = l_name;
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

    public int getUser_ID() {

        return user_ID;
    }

    public String getF_name() {
        return F_name;
    }

    public String getL_name() {
        return L_name;
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
