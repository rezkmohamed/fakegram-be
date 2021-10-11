package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.entity.Post;

public class PostUtils {
	public static PostDTO postToDTO(Post post) {
		PostDTO postDTO = new PostDTO(post.getIdPost(),
									  post.getImg_post(), 
									  post.getDescription(), 
									  post.getProfile().getIdProfile());
		postDTO.setDate(post.getDate());

		return postDTO;
	}
	
	public static PostDTO postToCompleteDTO(Post post) {
		PostDTO postDTO = postToDTO(post);
		postDTO.setLikesCounter(post.getLikes().size());
		postDTO.setProfile(ProfileUtils.profileToDTO(post.getProfile()));
		
		return null;
	}
	
	public static List<PostDTO> postToCompleteDTO(List<Post> posts){
		List<PostDTO> ris = new ArrayList<>();
		for(Post p : posts) {
			PostDTO postDTO = postToDTO(p);
			postDTO.setLikesCounter(p.getLikes().size());
			postDTO.setProfile(ProfileUtils.profileToDTO(p.getProfile()));
			ris.add(postDTO);
		}
		
		return ris;
	}
	
	public static List<PostDTO> postToDTO(List<Post> posts){
		List<PostDTO> ris = new ArrayList<>();
		for(Post p : posts) {
			ris.add(postToDTO(p));
		}
		
		return ris;
	}
	
	public static Post DTOPostToPostEntity(PostDTO postDTO) {
		Post post = new Post(postDTO.getImg(), 
							 postDTO.getDescription(), 
							 new Date());

		return post;
	}
}
