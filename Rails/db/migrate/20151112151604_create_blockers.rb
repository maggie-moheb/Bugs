class CreateBlockers < ActiveRecord::Migration
  def change
    create_table :blockers do |t|

      t.references :blocker
      t.references :blocked

      t.timestamps null: false
    end
    add_foreign_key :blockers, :users
    add_foreign_key :blockeds, :users
  end
end