class SettingsController < ApplicationController
	def index
		@user = User.find(params[:user_id])
		@setting = @user.setting
		render json: @setting,  status: :ok

	end
end
