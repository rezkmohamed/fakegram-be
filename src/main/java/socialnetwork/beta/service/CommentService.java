package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.CommentDTO;

public interface CommentService {
	public List<CommentDTO> findAllCommentsForPost(String idPost);
	
	public String addComment(String idProfile, CommentDTO commentDTO);
}
