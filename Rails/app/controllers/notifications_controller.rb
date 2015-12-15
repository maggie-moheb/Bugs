class NotificationsController < ApplicationController

def new
   @notification= Notification.new
   @notification.user_id = params[:user_id]
   @notification.notification_follower_id = params[:follower_id]
   @notification.post_id = params[:post_id]
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
else
   render :json=>{:errors => @notification.errors.full_messages}
 end
end



def show


end



end
