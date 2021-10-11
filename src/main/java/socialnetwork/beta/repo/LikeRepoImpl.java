package socialnetwork.beta.repo;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import socialnetwork.beta.entity.Like;

@Repository
public class LikeRepoImpl implements LikeRepo {
	@Autowired
	private EntityManager entityManager;

	@Override
	public String addLike(Like like) {
		Session session = entityManager.unwrap(Session.class);
		
		return (String)session.save(like);
	}

	@Override
	public boolean deleteLike(String idProfile, String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Like where id_profile_liker = :idProfile AND id_post = :idPost");
		query.setParameter("idProfile", idProfile);
		query.setParameter("idPost", idPost);
		if(query.executeUpdate() == 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public Like getLikeByProfileAndPost(String idProfile, String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query<Like> query = session.createQuery("from Like where id_profile_liker = :idProfile AND id_post = :idPost");
		query.setParameter("idProfile", idProfile); 
		query.setParameter("idPost", idPost);
		try {
			Like like = query.getSingleResult();
			return like;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
