class UsersController < ApplicationController

before_action :set_user, only: [:show, :update, :destroy]

  def index
    @users = User.all
    render json: @users if stale?(@users)
  end

  def show
    @user = User.find(params[:id])
    render json: @user if stale?(@user)
  end

  def create
    @user = User.new(user_params)
    if @user.save
      render json: @user, status: :created
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  def update
    if @user.update(user_params)
      render json: @user, status: :ok
    else
      render json: @user.errors, status: :unprocessable_entity
    end
  end

  def destroy
    @user.destroy
    head :no_content
  end

  private
  def set_user
    @user = User.find(params[:id])
  end

  def user_params
    params.require(:user).permit(:f_name, :l_name, :password, :email, :city, :country, :date_of_birth, :gender, :profile_picture, :facebook_access_token, :access_token)
  end  
end

