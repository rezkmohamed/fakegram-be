package socialnetwork.beta.repo;

import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.query.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import socialnetwork.beta.entity.Question;

@Repository
public class QuestionRepoImpl implements QuestionRepo {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Question> findQuestionsForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Question> query = session.createQuery("from Question where id_profile_responder = :idProfile AND answered = 1 ORDER BY date DESC");
		query.setParameter("idProfile", idProfile);
		List<Question> questions = query.getResultList();
		
		return questions;
	}
	
	@Override
	public List<Question> findPendingQuestionsForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Question> query = session.createQuery("from Question where id_profile_responder = :idProfile AND answered = 0 ORDER BY date DESC");
		query.setParameter("idProfile", idProfile);
		List<Question> questions = query.getResultList();
		
		return questions;
	}
	
	@Override
	public Question findPendingQuestion(String idQuestion) {
		Session session = entityManager.unwrap(Session.class);
		Query<Question> query = session.createQuery("from Question where id_question = :idQuestion AND answered = 0");
		query.setParameter("idQuestion", idQuestion);
		try {
			Question question = query.getSingleResult();
			return question;
		} catch (Exception e) {
			// TODO: nothing to do
		}
		
		return null;
	}
	
	@Override
	public Question findQuestionById(String idQuestion) {
		Session session = entityManager.unwrap(Session.class);
		Query<Question> query = session.createQuery("from Question where id_question = :idQuestion");
		query.setParameter("idQuestion", idQuestion);
		try {
			Question question = query.getSingleResult();
			return question;
		} catch (Exception e) {
			// TODO: nothing to do
		}

		return null;
	}

	@Override
	public String addNewQuestion(Question question) {
		Session session = entityManager.unwrap(Session.class);
		
		return (String)session.save(question);
	}

	@Override
	public int updateQuestion(String idQuestion, String answer) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Question set answer = :newAnswer, answered = 1 WHERE id_question = :idQuestion");
		query.setParameter("newAnswer", answer);
		query.setParameter("idQuestion", idQuestion);
		
		return query.executeUpdate();
	}

	@Override
	public int setQuestionToPending(String idQuestion) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("update Question set answer = '', answered = 0 WHERE id_question = :idQuestion");
		query.setParameter("idQuestion", idQuestion);
		
		return query.executeUpdate();
	}
}
