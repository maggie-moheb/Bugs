class CreatePosts < ActiveRecord::Migration
  def change
    create_table :posts do |t|
      t.string :title ,uniqueness: true
      t.text :text 
      t.text :photo
      t.references :user
      #t.references :comment   .....................check
      t.references :user_dest
      t.timestamps null: false
    end
    #add_foreign_key :posts, :comments .......................check
    add_foreign_key :posts, :users
    add_foreign_key :user_dests, :users
  end
end
