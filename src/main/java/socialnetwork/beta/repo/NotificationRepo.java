package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Notification;

public interface NotificationRepo {
	public List<Notification> getNotificationsForProfile(String idProfile);
	
	public int deleteFollowNotification(String idProfileNotificator, String idProfileToNotify);
	
	public int deleteLikeNotification(String idProfileNotificator, String idProfileToNotify);
	
	public String addNewNotification(Notification notification);
	
	public void setNotificationAsSeenForProfile(String idProfile);
}
