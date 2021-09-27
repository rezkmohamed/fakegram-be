package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.entity.Post;

public class PostUtils {
	public static PostDTO postToDTO(Post post) {
		/**
		 * FIXME
		 */
		return null;
	}
	
	public static List<PostDTO> postToDTO(List<Post> posts){
		List<PostDTO> ris = new ArrayList<>();
		for(Post p : posts) {
			ris.add(postToDTO(p));
		}
		
		return ris;
	}
	
	public static Post DTOPostToPostEntity(PostDTO postDTO) {
		/**
		 * FIXME
		 */
		return null;
	}
}
