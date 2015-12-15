package models;

/**
 * Created by maggiemoheb on 12/15/15.
 */
public class Setting {
    int id;
    boolean followers_can_post;
    boolean notify_comments;
    boolean notify_post;
    boolean notify_followers;
    int user_id;

    public int getId() {
        return this.id;
    }
    public boolean isFollowers_can_post() {
        return this.followers_can_post;
    }
    public boolean isNotify_comments() {
        return this.notify_comments;
    }
    public boolean isNotify_post() {
        return this.notify_post;
    }
    public boolean isNotify_followers() {
        return this.notify_followers;
    }
    public int getUser_id() {
        return this.getUser_id();
    }
}
