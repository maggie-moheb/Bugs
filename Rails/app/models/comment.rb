class Comment < ActiveRecord::Base
# skip_before_action :verify_authenticity_token

    belongs_to :user
	belongs_to :post
	 validates :user, presence: true
	 validates :post, presence: true
end

