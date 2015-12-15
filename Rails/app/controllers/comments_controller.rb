class CommentsController < ApplicationController


def new
  @comment= Comment.new
  @comment.user_id = params[:user_id]
  @comment.post_id = params[:post_id]
  @comment.text =  params[:text]
  if @comment.save
    render json: @comment
  else
    render :json=>{:errors => @comment.errors.full_messages}
   end
  end


def get_comments_on_post


  @post_id =params[:post_id]
  @comments = Comment.where(post_id: @post_id)
    unless @comments.empty?
      render json: @comments
    end
   end
end
