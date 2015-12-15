class CreateComments < ActiveRecord::Migration
  def change
    create_table :comments do |t|
      t.text :text

      t.references :user
      t.references :post
      t.timestamps null: false
    end
    add_foreign_key :comments, :posts
    add_foreign_key :comments, :users
  end
end
