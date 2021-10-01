package socialnetwork.beta.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import socialnetwork.beta.entity.Follow;

@Repository
public class FollowRepoImpl implements FollowRepo {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Follow> getFollowersForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_followed = :idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> followers = query.getResultList();
		
		return followers;
	}

	@Override
	public List<Follow> getFollowingForProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_follower = :idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> following = query.getResultList();
		
		return following;
	}
	
	@Override
	public Follow getFollow(String idProfileFollower, String idProfileFollowed) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_follower = :idProfileFollower AND id_followed = :idProfileFollowed");
		query.setParameter("idProfileFollower", idProfileFollower);
		query.setParameter("idProfileFollowed", idProfileFollowed);
		try {
			Follow follow = query.getSingleResult();
			return follow;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	@Override
	public String addFollow(Follow follow) {
		Session session = entityManager.unwrap(Session.class);
		
		return (String)session.save(follow);
	}

	@Override
	public boolean deleteFollow(String idProfileFollower, String idProfileFollowed) {
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createQuery("delete from Follow where id_follower = :idFollower AND id_followed = :idFollowed");
		query.setParameter("idFollower", idProfileFollower);
		query.setParameter("idFollowed", idProfileFollowed);
		if(query.executeUpdate() != 1) {
			return false;
		}

		return true;
	}
}
