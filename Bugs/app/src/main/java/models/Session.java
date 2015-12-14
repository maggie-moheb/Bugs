package models;

public class Session {
    private String access_token;
    private int user_id;

    public Session(String access_token,int user_id) {
        this.access_token=access_token;
        this.user_id=user_id;
    }

    public String getToken() {
        return access_token;
    }

    public void setToken(String token) {
        this.access_token = token;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }
}
