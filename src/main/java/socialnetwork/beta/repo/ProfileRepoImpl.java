package socialnetwork.beta.repo;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.Query;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.entity.Like;
import socialnetwork.beta.entity.Profile;

@Repository
public class ProfileRepoImpl implements ProfileRepo{
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Profile> findProfilesLikesPost(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query<Like> query = session.createQuery("from Like where id_post = :idPost");
		query.setParameter("idPost", idPost);
		List<Like> likes = query.getResultList();
		List<Profile> profiles = likes.stream().map(l -> l.getProfileLiker()).collect(Collectors.toList());
		
		return profiles;
	}

	@Override
	public List<Profile> findFollowersProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_followed = :idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> followers = query.getResultList();
		List<Profile> profiles = followers.stream().map(f -> f.getFollower()).collect(Collectors.toList());
		
		return profiles;
	}

	@Override
	public List<Profile> findFollowingProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Follow> query = session.createQuery("from Follow where id_follower = :idProfile");
		query.setParameter("idProfile", idProfile);
		List<Follow> following = query.getResultList();
		List<Profile> profiles = following.stream().map(f -> f.getFollowed()).collect(Collectors.toList());
		
		return profiles;
	}
	
	@Override
	public List<Profile> findProfilesByName(String nameLike) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile where name like :nameLike");
		query.setParameter("nameLike", "%"+nameLike+"%");
		List<Profile> profiles = query.getResultList();
		
		return profiles;
	}

	@Override
	public Profile findProfileByEmail(String email) {
		Session session = entityManager.unwrap(Session.class);
		Query<Profile> query = session.createQuery("from Profile where email = :e");
		query.setParameter("e", email);
		try {
			Profile profile = query.getSingleResult();
			return profile;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Profile findProfile(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Profile profile = session.get(Profile.class, idProfile);
		
		return profile;
	}

	@Override
	public void saveProfile(Profile profile) {
		Session session = entityManager.unwrap(Session.class);
		session.save(profile);
	}

	@Override
	public void updateProfile(Profile profile) {
		Session session = entityManager.unwrap(Session.class);
		
		session.update(profile);		
	}
}
