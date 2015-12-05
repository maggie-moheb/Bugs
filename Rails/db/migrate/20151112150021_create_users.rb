class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :f_name
      t.string :l_name
      t.string :city
      t.string :country
      t.date :date_of_birth
      t.boolean :gender
      t.text :profile_picture
      t.string :facebook_access_token
      t.string :access_token
      t.string :password
      t.string :email

      t.timestamps null: false
    end
  end
end
