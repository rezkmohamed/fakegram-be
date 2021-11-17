package socialnetwork.beta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import socialnetwork.beta.dto.PostDTO;


public interface PostService {
	public List<PostDTO> findAllPosts();
	
	public List<PostDTO> findPostsProfilePage(String idProfile);
	
	public PostDTO findPostById(String idPost);
	
	public String savePost(PostDTO post);
	
	public String savePostWithFileImg(MultipartFile img, String description, String idProfile);
	
	public boolean deletePostById(String idProfile, String idPost);
}
