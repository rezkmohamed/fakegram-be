package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Follow;

public interface FollowRepo {
	public List<Follow> getFollowersForProfile(String idProfile);
	
	public List<Follow> getFollowingForProfile(String idProfile);
	
	public String addFollow(Follow follow);
	
	public Follow getFollow(String idProfileFollower, String idProfileFollowed);
	
	public boolean deleteFollow(String idProfileFollower, String idProfileFollowed);
}
