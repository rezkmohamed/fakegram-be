package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.NotificationDTO;
import socialnetwork.beta.entity.Notification;

public class NotificationUtils {
	public static NotificationDTO notificationEntityToDTO(Notification notification) {
		/**
		 * TODO
		 */
		
		return null;
	}
	
	public List<NotificationDTO> notificationEntityToDTO(List<Notification> notifications){
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
