package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.NotificationDTO;
import socialnetwork.beta.repo.NotificationRepo;

@Service
public class NotificationServiceImpl implements NotificationService {
	@Autowired
	private NotificationRepo notificationRepo;
	

	@Override
	@Transactional
	public List<NotificationDTO> getNotificationsForProfile(String idProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int deleteFollowNotification(String idProfileNotificator, String idProfileToNotify) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public NotificationDTO addNotification(NotificationDTO notification) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean setNotificationsAsSeenForProfile(String idProfile) {
		// TODO Auto-generated method stub
		return false;
	}

}
