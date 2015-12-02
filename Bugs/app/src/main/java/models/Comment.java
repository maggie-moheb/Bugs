package models;

/**
 * Created by maggiemoheb on 12/1/15.
 */
public class Comment {
    int post_id;
    String text;
    int user_id;

    public Comment(int post_id, String text, int user_id) {
        this.post_id = post_id;
        this.text = text;
        this.user_id = user_id;
    }

    public int getPost_id() {
        return this.post_id;
    }
    public String getText() {
        return this.text;
    }
    public int getUser_id() {
        return  this.user_id;
    }
    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
