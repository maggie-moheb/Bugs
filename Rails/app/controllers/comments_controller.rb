class CommentsController < ApplicationController
  skip_before_action :verify_authenticity_token

  #Returns comments with a given user id for a specific post. 
  # GET /comments/1
  # GET /comments/1.json
  def index
    @user=User.find(params[:user_id])
    @posts=@user.posts.find(params[:post_id])
    @comments = @posts.comments.all
    render json: @comments if stale?(@comments)
  end
  def show
   @user=User.find(params[:user_id])
    @posts=@user.posts.find(params[:post_id])
    @comment = @posts.comments.find(params[:id])
    render json: Comment.find(@comment.id) if stale?(Comment.find(@comment.id))
  end

  #Creates comment with comment parameters for a specific post.
  # POST /comments
  # POST /comments.json
   def create

    @user=User.find(params[:user_id])
    @post = @user.posts.find(params[:post_id])
    @comment = @post.comments.create(comment_params)
    if @comment.save
      render json: @comment, status: :created
    else
      render json: @comment.errors, status: :unprocessable_entity
    end
  end

   def getcommenter
    @commenter = User.where(:id => params[:user_id])
    render json: @commenter if stale?(@commenter)
  end
  # # Never trust parameters from the scary internet, only allow the white list through.
    private
  def comment_params
    params.require(:comment).permit(:text,:user_id,:post_id)
  end
end
