package models;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class Post {
    private int id;
    private String title;
    private String text;
    private String photoDir;
    private int user_id;
    private int user_dest_id;

    public int getId() {
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getText() {
        return this.text;
    }
    public String getPhoto() {
        return this.photoDir;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public int getUser_dest_id() {
        return this.user_dest_id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setPhoto(String photoDir) {
        this.photoDir = photoDir;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setUser_dest_id(int user_dest_id) {
        this.user_dest_id = user_dest_id;
    }
}
