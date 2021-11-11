package socialnetwork.beta.repo;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.query.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import socialnetwork.beta.entity.Notification;

@Repository
public class NotificationRepoImpl implements NotificationRepo {
	@Autowired
	private EntityManager entityManager;


	@Override
	public List<Notification> getNotificationsForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Notification> query = session.createQuery("from Notification where id_profile_to_notify = :idProfile ORDER BY date DESC");
		query.setParameter("idProfile", idProfile);
		List<Notification> notifications = query.getResultList();		
		
		return notifications;
	}

	@Override
	public int deleteFollowNotification(String idProfileNotificator, String idProfileToNotify) {		
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Notification where id_profile_notificator = :idProfile1 AND id_profile_to_notify = :idProfile2 AND notification_type = :notifType");
		query.setParameter("idProfile1", idProfileNotificator);
		query.setParameter("idProfile2", idProfileToNotify);
		query.setParameter("notifType", 3);
		
		return query.executeUpdate();
	}

	@Override
	public String addNewNotification(Notification notification) {
		Session session = entityManager.unwrap(Session.class);		
		
		return (String) session.save(notification);
	}

	@Override
	public void setNotificationAsSeenForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Notification set isseen = 1 where id_profile_to_notify = :idProfile AND isseen = 0");
		query.setParameter("idProfile", idProfile);
		query.executeUpdate();
	}

}
