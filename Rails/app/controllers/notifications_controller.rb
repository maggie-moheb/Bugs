class NotificationsController < ApplicationController

def new
   @notification= Notification.new
   @notification.user_id = params[:user_id]
   @notification.notification_follower_id = params[:follower_id]
   @post_id = params[:post_id]
     if @post_id.nil?
       @post_id = 300
     end
   @notification.post_id = @post_id
   @notification.text =  params[:text]

     if @notification.save
      render json: @notification
     else
      render :json=>{:errors => @notification.errors.full_messages}
     end


end




def view_all_notification
@user_id = params[:user_id]
@notification= Notification.where(user_id: @user_id)
unless @notification.empty?
   render json: @notification
 end
end



def show


@notification_id = params[:notification_id]
@notification = Notification.find(@notification_id)
  unless @notification.nil?
   @user_id = @notification.user_id
   @notification_follower_id = @notification.notification_follower_id
   @post_id = @notification.post_id
   @text = @notification.text
   @follower = User.find(@notification_follower_id)
   @follow_msg = @follower.f_name+" "+"sent you a follow request"
   @comment_msg= @follower.f_name+" "+"commented on your post"
   @posted_msg = @follower.f_name+" "+"posted on your wall"

     unless(@text == @comment_msg)
       redirect_to comment_path(post_id: @post_id)
      else(@text == @posted_msg)
        redirect_to posts_path(post_id: @post_id)
      # else(@text == @follow_msg)
      #  redirect_to users_path(user_id: @follower)

     end
  end
end

end
