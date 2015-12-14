class Follower < ActiveRecord::Base
    belongs_to :follower, :class_name=> "User"
	belongs_to :followee, :class_name=> "User"
	validates :follower, presence: true
    validates :followee, presence: true
    scope :can_post, -> { where(can_post: true) }
  
end
