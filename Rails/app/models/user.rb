class User < ActiveRecord::Base

    has_many :posts, dependent: :destroy
	has_many :user_dests, :class_name => "Post", foreign_key: "user_dest_id", dependent: :destroy
	has_many :comments, dependent: :destroy
	has_one :setting, dependent: :destroy
	has_many :notifications, dependent: :destroy
	
	has_many :followers, :class_name => "Follower", foreign_key: "followee_id", dependent: :destroy
	has_many :followees, :class_name => "Follower", foreign_key: "follower_id", dependent: :destroy
	
	has_many :notification_followers, :class_name => "Notification", foreign_key: "notification_follower_id", dependent: :destroy

	has_many :blockers, :class_name => "Blocker", foreign_key: "blocked_id", dependent: :destroy
	has_many :blockeds, :class_name => "Blocker", foreign_key: "blocker_id", dependent: :destroy
	

def self.authenticate(password)
  if user.find_by_password(password)
    return true
  else
    return false
  end
end

before_save :encrypt_password
def encrypt_password
  if password.present?
    self.password= BCrypt::Password.create(password)
  end
end

 before_save :generate_access_token
def generate_access_token
    begin
      self.access_token = SecureRandom.hex
    end while self.class.exists?(access_token: access_token)
end
 
  VALID_EMAIL_REGEX = /\A[\w+\-.]+@[a-z\d\-.]+\.[a-z]+\z/i 
  validates :email, presence: true, length: { maximum: 255 },
                    format: { with: VALID_EMAIL_REGEX, message:"%{value} is not a valid email" },
                   uniqueness: { case_sensitive: false }
                   
	validates :f_name,  presence: true, length: { maximum: 25, too_long: "%{count} characters is the maximum allowed"}
	validates :l_name,  presence: true, length: { maximum: 25, too_long: "%{count} characters is the maximum allowed"}
	validates :password,  presence: true, length: { minimum: 6, too_short: "6 characters is the minimum allowed"}
	validates :gender, inclusion: { in: [true,false]}
	scope :male, -> { where("gender = ?", true) }
    scope :female, -> { where("gender = ?", false) }
end
