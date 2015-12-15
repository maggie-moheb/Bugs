Rails.application.routes.draw do

    get 'users/:id/followers'=> 'followers#findFollowers'
    get 'users/:id/followees'=> 'followers#findFollowees'
    resources :users do
      resources :settings
      resources :followers 
      resources :posts do
        resources :comments do
        end
      end
    end

    
  #     member do
  #       get 'short'
  #       post 'toggle'
  #     end
  #
  #     collection do
  #       get 'sold'
  #     end
  #   end

  # Example resource route with sub-resources:
  #   resources :products do
  #     resources :comments, :sales
  #     resource :seller
  #   end

  # Example resource route with more complex sub-resources:
  #   resources :products do
  #     resources :comments
  #     resources :sales do
  #       get 'recent', on: :collection
  #     end
  #   end

  # Example resource route with concerns:
  #   concern :toggleable do
  #     post 'toggle'
  #   end
  #   resources :posts, concerns: :toggleable
  #   resources :photos, concerns: :toggleable

  # Example resource route within a namespace:
  #   namespace :admin do
  #     # Directs /admin/products/* to Admin::ProductsController
  #     # (app/controllers/admin/products_controller.rb)
  #     resources :products
  #   end
  get 'v/users/:user_id/posts/:title'=>'posts#getpostID'
  get 'v/users/:user_id/posts/:post_id/comments/:user_id'=>'comments#getcommenter'

  get 'users/:id/followers'=> 'followers#findFollowers'
  get 'users/:id/followees'=> 'followers#findFollowees'
 # get 'users'=> 'users#index'
end
