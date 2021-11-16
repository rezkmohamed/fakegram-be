package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.NotificationDTO;
import socialnetwork.beta.dto.NotificationTypeDTO;
import socialnetwork.beta.entity.Comment;
import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.entity.Like;
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
	
	public static Notification newNotificationEntityFromLike(Like like) {
		Notification notification = new Notification();
		notification.setNotificationType(NotificationTypeDTO.LIKE);
		notification.setProfileNotificator(like.getProfileLiker());
		notification.setProfileToNotify(like.getPost().getProfile());
		notification.setSeen(false);
		notification.setPost(like.getPost());
		
		return notification;
	}
	
	public static Notification newNotificationEntityFromComment(Comment comment) {
		/**
		 * TODO
		 */
		
		return null;
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
			notificationDTO.setPost(PostUtils.postToDTO(notification.getPost()));
		} else if (notificationDTO.getNotificationType() == NotificationTypeDTO.LIKE) {
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
		Notification notification = new Notification();
		notification.setProfileNotificator(ProfileUtils.DTOProfileToProfileEntity(notificationDTO.getProfileNotificator()));
		notification.setProfileToNotify(ProfileUtils.DTOProfileToProfileEntity(notificationDTO.getProfileToNotify()));
		notification.setNotificationType(notificationDTO.getNotificationType());
		if(notification.getNotificationType() == NotificationTypeDTO.COMMENT) {
			notification.setComment(notificationDTO.getCommentMessage());
			notification.setPost(PostUtils.DTOPostToPostEntity(notificationDTO.getPost()));
		} else if(notification.getNotificationType() == NotificationTypeDTO.LIKE) {
			notification.setPost(PostUtils.DTOPostToPostEntity(notificationDTO.getPost()));
		}
		
		return notification;
	}
}
