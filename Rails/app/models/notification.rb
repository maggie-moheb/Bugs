class Notification < ActiveRecord::Base
    belongs_to :user 
    belongs_to :post
    belongs_to :notification_follower, :class_name=> "User"
    validates :notification_follower, presence: true 
    validates :user, presence: true
    validates :post, presence: true
    validates :text, presence: true
end
