package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Comment;

public interface CommentRepo {
	public List<Comment> findAllCommentsForPost(String idPost);
	
	public String addComment(Comment comment);
}
