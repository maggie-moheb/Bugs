package models;

/**
 * Created by maggiemoheb on 12/15/15.
 */
public class Follower {
    int id;
    boolean can_post;
    int follower_id;
    int followee_id;

    public int getId() {
        return this.id;
    }
    public boolean isCan_post() {
        return this.can_post;
    }
    public int getFollower_id() {
        return this.follower_id;
    }
    public int getFollowee_id(){
        return this.followee_id;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setCan_post(boolean can_post) {
        this.can_post = can_post;
    }
    public void setFollower_id(int follower_id) {
        this.follower_id = follower_id;
    }
    public void setFollowee_id(int followee_id) {
         this.followee_id = followee_id;
    }
}
