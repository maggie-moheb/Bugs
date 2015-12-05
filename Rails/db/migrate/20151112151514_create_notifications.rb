class CreateNotifications < ActiveRecord::Migration
  def change
    create_table :notifications do |t|
      t.text :text

      t.references :user
      t.references :notification_follower
      t.references :post
      t.timestamps null: false
    end
    add_foreign_key :notifications, :users
    add_foreign_key :notification_follower, :users
    add_foreign_key :notifications, :posts
    


    

  end
end
