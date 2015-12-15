class Setting < ActiveRecord::Base
    belongs_to :user
    
    validates :user, presence: true
    validates :notify_followers, inclusion: { in: [false,true]}
    validates :notify_comments, inclusion: { in: [false,true]}
    validates :notify_post, inclusion: { in: [false,true]}
    validates :followers_can_post, inclusion: { in: [false,true]}
    
  after_initialize :default_values
  def default_values
    self.notify_followers ||= true
    self.notify_comments ||= true
    self.notify_post ||= true
    self.followers_can_post ||= true
  end
end
