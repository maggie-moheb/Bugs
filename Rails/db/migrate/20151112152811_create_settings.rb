class CreateSettings < ActiveRecord::Migration
  def change
    create_table :settings do |t|
      t.boolean :followers_can_post
      t.boolean :notify_comments
      t.boolean :notify_post
      t.boolean :notify_followers

      t.references :user

      t.timestamps null: false
    end
    add_foreign_key :settings, :users
    # add_foreign_key :settings, :comments
    # add_foreign_key :settings, :posts
    # add_foreign_key :settings, :followers
    

  end
end
