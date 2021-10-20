package socialnetwork.beta.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import socialnetwork.beta.entity.Conversation;
import socialnetwork.beta.entity.Message;

@Repository
public class MessageAndConversationRepoImpl implements MessageAndConversationRepo{
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Message> getAllMessages() {
		Session session = entityManager.unwrap(Session.class);
		Query<Message> query = session.createQuery("from Message", Message.class);
		List<Message> messages = query.getResultList();
		
		return messages;
	}

	@Override
	public List<Message> getMessagesOfChat(String idConversation) {
		Session session = entityManager.unwrap(Session.class);
		Query<Message> query = session
				.createQuery("from Message where id_conversation = :idConversation ORDER BY date_message DESC",
						Message.class);
		query.setParameter("idConversation", idConversation);
		List<Message> messages = query.getResultList();
		
		return messages;
	}

	@Override
	public List<Conversation> getConversationsForProfile(String idProfileLogged) {
		Session session = entityManager.unwrap(Session.class);
		Query<Conversation> query = session.createQuery("from Conversation where id_profile1 = :idProfile1 OR id_profile2 = :idProfile2");
		query.setParameter("idProfile1", idProfileLogged); query.setParameter("idProfile2", idProfileLogged);
		List<Conversation> conversations = query.getResultList();
		
		return conversations;
	}

	@Override
	public Conversation getConversation(String idConversation) {
		Session session = entityManager.unwrap(Session.class);
		Query<Conversation> query = session.createQuery("from Conversation where id_conversation = :idConv");
		query.setParameter("idConv", idConversation);
		try {
			Conversation conv = query.getSingleResult();
			return conv;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public Conversation getConversation(String idProfile1, String idProfile2) {
		Session session = entityManager.unwrap(Session.class);
		Query<Conversation> query = session.createQuery("from Conversation where id_profile1 = :idProfile1 AND id_profile2 = :idProfile2");
		query.setParameter("idProfile1", idProfile1); query.setParameter("idProfile2", idProfile2);
		try {
			Conversation conv = query.getSingleResult();
			return conv;
		} catch (Exception e) {
			Query<Conversation> query2 = session.createQuery("from Conversation where id_profile1 = :idProfile2 AND id_profile2 = :idProfile1");
			query2.setParameter("idProfile1", idProfile1); query2.setParameter("idProfile2", idProfile2);
			try {
				Conversation conv = query2.getSingleResult();
				return conv;
			} catch (Exception e2) {
			}
		}
		return null;
	}

	@Override
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Message set isseen = 1 where id_conversation = :idConversation AND id_profile_reciver = :idProfile AND isseen = 0");
		query.setParameter("idProfile", idLoggedProfile);
		query.setParameter("idConversation", idConversation);
		int ris = query.executeUpdate();
		if(ris < 1) {
			return false;
		}
		
		return true;
	}

	@Override
	public String addMessage(Message message) {
		Session session = entityManager.unwrap(Session.class);
		session.save(message);
		
		return (String) session.save(message);
	}

	@Override
	public String createNewConversation(Conversation conversation) {
		Session session = entityManager.unwrap(Session.class);
		String idNewConversation = (String) session.save(conversation);
		if(!idNewConversation.equals("")) {
			return idNewConversation;
		}
		
		return null;
	}	
}
