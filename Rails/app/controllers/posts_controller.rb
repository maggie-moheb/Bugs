class PostsController < ApplicationController
  #Returns posts with a given user id for a specific user. 
  # GET /posts/1
  # GET /posts/1.json
  def index
    @user=User.find(params[:user_id])
    @posts=@user.posts.all
    render json: @posts if stale?(@posts)
  end

   def getpostID
    @user = User.find(params[:user_id])
    @post = @user.posts.where(:title => params[:title])
    @id = @post
    render json: @id if stale?(@id)
  end

def show
    @user=User.find(params[:user_id])
    @post=@user.posts.find(params[:id])
    render json: User.find(@post.id) if stale?(User.find(@post.id))
  end
  
 # Never trust parameters from the scary internet, only allow the white list through.
  def post_params
    params.require(:post).permit(:title,:text, :photo,:user_id,:user_dest,:id)
  end

end
