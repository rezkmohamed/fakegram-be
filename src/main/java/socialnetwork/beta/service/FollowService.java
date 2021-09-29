package socialnetwork.beta.service;


public interface FollowService {	
	public String addFollow(String idProfileFollower, String idProfileFollowed);
	
	public boolean deleteFollow(String idProfileFollower, String idProfileFollowed);
}
