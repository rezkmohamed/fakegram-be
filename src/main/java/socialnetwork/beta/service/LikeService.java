package socialnetwork.beta.service;

import socialnetwork.beta.dto.LikeDTO;

public interface LikeService {
	public String addLike(LikeDTO likeDTO);
	
	public boolean deleteLike(String idProfile, String idPost);
	
	public boolean getLikeByProfileAndPost(String idProfile, String idPost);
}
