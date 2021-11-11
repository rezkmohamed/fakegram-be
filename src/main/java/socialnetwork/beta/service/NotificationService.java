package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.NotificationDTO;

public interface NotificationService {
	public List<NotificationDTO> getNotificationsForProfile(String idProfile);
	
	public int deleteFollowNotification(String idProfileNotificator, String idProfileToNotify);
	
	public NotificationDTO addNotification(NotificationDTO notification);
	
	public boolean setNotificationsAsSeenForProfile(String idProfile);
}
