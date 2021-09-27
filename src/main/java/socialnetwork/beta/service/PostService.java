package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.PostDTO;


public interface PostService {
	public List<PostDTO> findAllPosts();
	
	public List<PostDTO> findPostsProfilePage(String idProfile);
	
	public PostDTO findPostById(String idPost);
	
	public String savePost(PostDTO post);
	
	public boolean deletePostById(String idPost);
}
