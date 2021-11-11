package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.NotificationDTO;
import socialnetwork.beta.dto.NotificationTypeDTO;
import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.entity.Notification;

public class NotificationUtils {
	public static Notification newNotificationEntityFromFollow(Follow follow) {
		Notification notification = new Notification();
		notification.setNotificationType(NotificationTypeDTO.FOLLOW);
		notification.setProfileNotificator(follow.getFollower());
		notification.setProfileToNotify(follow.getFollowed());
		notification.setSeen(false);
		
		return notification;
	}
	
	
	public static NotificationDTO notificationEntityToDTO(Notification notification) {
		NotificationDTO notificationDTO = new NotificationDTO();
		notificationDTO.setProfileNotificator(ProfileUtils.profileToDTO(notification.getProfileNotificator()));
		notificationDTO.setProfileToNotify(ProfileUtils.profileToDTO(notification.getProfileToNotify()));
		notificationDTO.setDate(notification.getDate());
		notificationDTO.setIsSeen(notification.isSeen());
		notificationDTO.setImgProfileNotificator(notification.getProfileNotificator().getProPic());
		notificationDTO.setNotificationType(notification.getNotificationType());
		if(notificationDTO.getNotificationType() == NotificationTypeDTO.COMMENT) {
			notificationDTO.setCommentMessage(notification.getComment());
		} else if (notificationDTO.getNotificationType() == NotificationTypeDTO.COMMENT) {
			notificationDTO.setPost(PostUtils.postToDTO(notification.getPost()));
		}
		
		
		return notificationDTO;
	}
	
	public static List<NotificationDTO> notificationEntityToDTO(List<Notification> notifications){
		List<NotificationDTO> notificationsDTO = new ArrayList<>();
		for(Notification n : notifications) {
			notificationsDTO.add(notificationEntityToDTO(n));
		}
		
		return notificationsDTO;
	}
	
	public static Notification notificationDTOToEntity(NotificationDTO notificationDTO) {
		/**
		 * TODO
		 */
		
		return null;
	}
}
