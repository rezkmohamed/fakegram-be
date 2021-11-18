package socialnetwork.beta.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.NotificationDTO;
import socialnetwork.beta.dto.NotificationTypeDTO;
import socialnetwork.beta.entity.Notification;
import socialnetwork.beta.repo.NotificationRepo;
import socialnetwork.beta.utils.ImgUtils;
import socialnetwork.beta.utils.NotificationUtils;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private NotificationRepo notificationRepo;
	@Autowired
	private ImgUtils imgUtils;
	
	
	
	@Override
	@Transactional
	public List<NotificationDTO> getNotificationsForProfile(String idProfile) {
		List<Notification> notifications = notificationRepo.getNotificationsForProfile(idProfile);
		List<NotificationDTO> notificationsDTO = NotificationUtils.notificationEntityToDTO(notifications);
		notificationsDTO.stream()
		.forEach(n -> {
			if(n.getNotificationType() != NotificationTypeDTO.QUESTION) {
				if(n.getProfileNotificator().getProPic() != null) {
					try {
						n.getProfileNotificator().setProPic(imgUtils.fileImgToBase64Encoding(n.getProfileNotificator().getProPic()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		
		return notificationsDTO;
	}

	@Override
	@Transactional
	public int deleteFollowNotification(String idProfileNotificator, String idProfileToNotify) {
		return notificationRepo.deleteFollowNotification(idProfileNotificator, idProfileToNotify);
	}

	@Override
	@Transactional
	public String addNotification(NotificationDTO notification) {
		Notification notificationToAdd = NotificationUtils.notificationDTOToEntity(notification);
		
		return notificationRepo.addNewNotification(notificationToAdd);
	}

	@Override
	@Transactional
	public void setNotificationsAsSeenForProfile(String idProfile) {
		notificationRepo.setNotificationAsSeenForProfile(idProfile);
	}
}
