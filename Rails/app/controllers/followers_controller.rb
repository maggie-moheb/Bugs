class FollowersController < ApplicationController
    def findFollowers
    	@user = User.find(params[:user_id])
    	@followers = @user.followers.all
    	render json: @followers if stale?(@followers)
    end
end
