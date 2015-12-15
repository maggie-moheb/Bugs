Rails.application.routes.draw do

    get 'users/:id/followers'=> 'followers#findFollowers'
    get 'users/:id/followees'=> 'followers#findFollowees'
    resources :users do
      resources :followers 
      resources :settings
    end
    
  
end
