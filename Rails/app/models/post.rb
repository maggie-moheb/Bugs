class Post < ActiveRecord::Base
    belongs_to :user
	belongs_to :user_dest, :class_name => "User"
	has_many   :comments ,dependent: :destroy
	has_many   :notifications,dependent: :destroy
	
    validates :user, presence: true
    validates :user_dest, presence: true
	validates :text, presence:true
	validates :title, length: { maximum:255 }, allow_blank: true
	
	scope :AI_topic, -> { where("topic =?", 1) }
	scope :HCI_topic, -> { where("topic = ?", 2) }
	scope :Bugs_topic, -> { where("topic = ?", 3) }
	scope :Others_topic, -> { where("topic > ?", 3) }
	
end

