package socialnetwork.beta.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import socialnetwork.beta.entity.Comment;

@Repository
public class CommentRepoImpl implements CommentRepo {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Comment> findAllCommentsForPost(String idPost) {
		Session session = entityManager.unwrap(Session.class);
		Query<Comment> query = session.createQuery("from Comment where id_post = :idPost ORDER BY date DESC");
		query.setParameter("idPost", idPost);
		List<Comment> comments = query.getResultList();
		
		return comments;
	}

	@Override
	public String addComment(Comment comment) {
		Session session = entityManager.unwrap(Session.class);
				
		return (String)session.save(comment);
	}

}
