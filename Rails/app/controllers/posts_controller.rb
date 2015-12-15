class PostsController < ApplicationController

 def new
  @post= Post.new
  @post.user_id = params[:post_user_id]
  @post.user_dest_id = params[:post_user_dest]
  @post.title = params[:post_title]
  @post.text =  params[:post_text]
  @post.photo = params[:post_photo]
  if @post.save
    render json: @post
  else
    render :json=>{:errors => @post.errors.full_messages}
   end
  end

def show

@post = Post.find(params[:post_id])
unless @post.nil?
render json:@post
 end
end



end
