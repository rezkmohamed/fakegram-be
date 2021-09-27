package socialnetwork.beta.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import socialnetwork.beta.entity.Post;

@Repository
public class PostRepoImpl implements PostRepo {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Post> findAllPosts() {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post", Post.class);
		
		return query.getResultList();
	}

	@Override
	public List<Post> findPostsProfilePage(String idProfile) {
		Session session = entityManager.unwrap(Session.class);
		Query<Post> query = session.createQuery("from Post where id_profile=:idProfile");
		query.setParameter("idProfile", idProfile);
		List<Post> posts = query.getResultList();
		
		return posts;
	}

	@Override
	public Post findPostById(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Post post = session.get(Post.class, idPost);
		
		return post;
	}

	@Override
	public String savePost(Post post) {
		Session session = entityManager.unwrap(Session.class);
		return (String)session.save(post);
	}

	@Override
	public boolean deletePostById(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		
		Query query = session.createQuery("delete from Post where id_post = :idPost");
		query.setParameter("idPost", idPost); 
		if(query.executeUpdate() == 1) {
			return true;
		}
		return false;
	}

}
