class FollowersController < ApplicationController
    def findFollowers
    	@user = User.find(params[:id])
    	@followers = @user.followers.all
    	render json: @followers,  status: :ok
    end
end
