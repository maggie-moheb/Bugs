package models;

/**
 * Created by maggiemoheb on 11/30/15.
 */
public class Post {
    private int id;
    private String title;
    private String text;
    private String photo;
    private int user_id;
    private int user_dest;
    public Post(int id, String title, String text, String photoDir, int user_id, int user_dest_id) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.photo = photoDir;
        this.user_id = user_id;
        this.user_dest = user_dest_id;
    }
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
        return this.photo;
    }
    public int getUser_id() {
        return this.user_id;
    }
    public int getUser_dest() {
        return this.user_dest;
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
        this.photo = photoDir;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public void setUser_dest(int user_dest) {
        this.user_dest = user_dest;
    }
}
