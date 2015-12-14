class CreateFollowers < ActiveRecord::Migration
  def change
    create_table :followers do |t|
      t.boolean :can_post
      t.references :follower
      t.references :followee
      t.timestamps null: false
    end
    add_foreign_key :followers, :users
    add_foreign_key :followees, :users
  end
end
