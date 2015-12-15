class FollowersController < ApplicationController
 #Returns followers for a specific user with given name. 
    def findFollowers
      @user = User.find(params[:id])
      @followers = @user.followers.all
      render json: @followers,  status: :ok
    end
    
    def findFollowees
    	@user = User.find(params[:id])
    	@followees = @user.followees.all
    	render json: @followees, status: :ok
    end
  #Returns list of followers for a specific user. 
  # GET /followers
  # GET /followers.json
  def index
    @user = User.find(params[:user_id])
    @followers = @user.followers.all
    render json: @followers if stale?(etag: @followers.all, last_modified: @followers.maximum(:updated_at))
  end

  #Returns follower with a given follower id for a specific user. 
  # GET /followers/1
  # GET /followers/1.json
  def show
    @user=User.find(params[:user_id])
    @follower=@user.followers.find(params[:id])
    render json: User.find(@follower.id) if stale?(User.find(@follower.id))
  end

  def getName
    @user=User.find(params[:user_id])
    @follower=@user.followers.find(params[:id])
    @name = @follower.name
    render json: @name if stale?(@name)
  end
  #Creates follower with follower parameters for a specific user.
  # POST /followers
  # POST /followers.json
   def create
    @user=User.find(params[:user_id])
    @follower = @user.followers.create(follower_params)
    if @follower.save
      render json: @follower, status: :created
    else
      render json: @follower.errors, status: :unprocessable_entity
    end
  end

  #Updates follower with given follower id for specific user.
  # PATCH/PUT /followers/1
  # PATCH/PUT /followers/1.json
  def update
    if @follower.update(follower_params)
      head :no_content
    else
      render json: @follower.errors, status: :unprocessable_entity
    end
  end
  
  #Deletes follower with given follower id for a specific user.
  # DELETE /followers/1
  # DELETE /followers/1.json
  def destroy
    @user = User.find(params[:user_id])
    @follower = @user.follower.find(params[:id])
    @follower.destroy
    head :no_content
  end

  private
  # Use callbacks to share common setup or constraints between actions.
  def set_follower
    @follower = Follower.find(params[:id])
  end
  # Never trust parameters from the scary internet, only allow the white list through.
  def follower_params
    params.require(:follower).permit(:f_name, :l_name, :email, :city, :country, :date_of_birth, :gender, :profile_picture, :can_post,:user_id)
  end

end
