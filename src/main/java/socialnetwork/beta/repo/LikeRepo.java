package socialnetwork.beta.repo;


import socialnetwork.beta.entity.Like;

public interface LikeRepo {
	public String addLike(Like like);
	
	public boolean deleteLike(String idProfile, String idPost);
	
	public Like getLikeByProfileAndPost(String idProfile, String idPost);
}	
