class Blocker < ActiveRecord::Base
    has_many :blocker, :class_name=> "User"
	has_many :blocked, :class_name=> "User"
	
	validates :blocker_id, presence: true
	validates :blocked_id, presence: true
end
